package com.ike.service.cognito.service.impl;

import java.net.URL;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.ike.service.common.exceptions.CognitoExceptions;

public class AwsCognitoRSAKeyProvider implements RSAKeyProvider {
	
	private URL aws_kid_store_url = null;

	public AwsCognitoRSAKeyProvider(String cognitoJwks) throws CognitoExceptions {
		String url = String.format(cognitoJwks);
		try {
			this.aws_kid_store_url = new URL(url);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			throw new CognitoExceptions("ERROR AL OBTENER LA URL DE JWSK DE COGNITO");
		}
	}

	@Override
	public RSAPublicKey getPublicKeyById(String keyId) {
		try {
			JwkProvider provider = new JwkProviderBuilder(aws_kid_store_url).build();
			Jwk jwk = provider.get(keyId);
			return (RSAPublicKey) jwk.getPublicKey();
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
		}
		return null;
	}

	@Override
	public RSAPrivateKey getPrivateKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrivateKeyId() {
		// TODO Auto-generated method stub
		return null;
	}

}
