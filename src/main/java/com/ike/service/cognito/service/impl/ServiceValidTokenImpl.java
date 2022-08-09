package com.ike.service.cognito.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.ike.service.cognito.dto.UserSingInRequest;
import com.ike.service.cognito.dto.UserSingInResponse;
import com.ike.service.cognito.service.ServiceValidToken;

@Service
public class ServiceValidTokenImpl implements ServiceValidToken {

	@Value("${cognito.jwks}")
	private String cognitoJwks;
	
	@Value("${aws.access.key}")
	private String aws_access_key;
	
	@Value("${aws.access.secret}")
	private String aws_access_secret;
	
	@Value("${aws.cognito.clientid}")
	private String aws_cognito_clientid;
	
	@Value("${aws.cognito.client.secret}")
	private String aws_cognito_client_secret;
	
	@Value("${aws.cognito.userpoolid}")
	private String aws_cognito_userpoolid;
	
	AWSCognitoIdentityProvider awsCognitoIdentityProvider = null;

	public boolean validToken(String token) {
		try {
			RSAKeyProvider key_provider = new AwsCognitoRSAKeyProvider(cognitoJwks);
			Algorithm algorithm = Algorithm.RSA256(key_provider);
			JWTVerifier jwt_verifier = JWT.require(algorithm).build();
			DecodedJWT decoded_jwt = jwt_verifier.verify(token);
			System.out.println("<=== TOKEN VALIDO ===>" + decoded_jwt.getToken());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UserSingInResponse getTokenCognito(UserSingInRequest userSingInRequest) {
		UserSingInResponse userSingInResponse = new UserSingInResponse();

		try {
			awsCognitoIdentityProvider = new AWSCognitoIdentityProviderClient(new BasicAWSCredentials(aws_access_key, aws_access_secret)).withRegion(Regions.US_EAST_1);
		} catch (Exception e) {
			e.getMessage();
		}

		Map<String, String> auth_params = new HashMap<>();
		auth_params.put("USERNAME", userSingInRequest.getUser_name());
		auth_params.put("PASSWORD", userSingInRequest.getPassword());
		auth_params.put("SECRET_HASH", calculateSecretHash(aws_cognito_clientid,
																	 aws_cognito_client_secret, 
																	 userSingInRequest.getUser_name()));

		AdminInitiateAuthRequest adminInitiateAuthRequest = new AdminInitiateAuthRequest()
																.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
																.withAuthParameters(auth_params)
																.withClientId(aws_cognito_clientid)
																.withUserPoolId(aws_cognito_userpoolid);

		AdminInitiateAuthResult adminInitiateAuthResult = awsCognitoIdentityProvider.adminInitiateAuth(adminInitiateAuthRequest);

		userSingInResponse.setAccess_token(adminInitiateAuthResult.getAuthenticationResult().getAccessToken());
		userSingInResponse.setExpires_in(adminInitiateAuthResult.getAuthenticationResult().getExpiresIn());
		userSingInResponse.setToken_type(adminInitiateAuthResult.getAuthenticationResult().getTokenType());
		
		return userSingInResponse;
	}
	
	public static String calculateSecretHash(String user_pool_client_id, String user_pool_client_secret, String user_name) {
		final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
		SecretKeySpec secret_key_spec = new SecretKeySpec(user_pool_client_secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGORITHM);
		try {
			Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
			mac.init(secret_key_spec);
			mac.update(user_name.getBytes(StandardCharsets.UTF_8));
			byte[] byte_mac = mac.doFinal(user_pool_client_id.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(byte_mac);
		} catch (Exception e) {
			throw new RuntimeException("Error while calculating");
		}
	}
}
