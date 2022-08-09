package com.ike.service.cognito.dto;

import java.io.Serializable;

public class CredentialsAWS implements Serializable {

	private static final long serialVersionUID = 1L;

	private String aws_access_key;
	private String aws_access_secret;
	private String aws_region;
	private String aws_cognito_clientid;
	private String aws_cognito_client_secret;
	private String aws_cognito_userpoolid;
	private String aws_jwks;

	public String getAws_access_key() {
		return aws_access_key;
	}

	public void setAws_access_key(String aws_access_key) {
		this.aws_access_key = aws_access_key;
	}

	public String getAws_access_secret() {
		return aws_access_secret;
	}

	public void setAws_access_secret(String aws_access_secret) {
		this.aws_access_secret = aws_access_secret;
	}

	public String getAws_region() {
		return aws_region;
	}

	public void setAws_region(String aws_region) {
		this.aws_region = aws_region;
	}

	public String getAws_cognito_clientid() {
		return aws_cognito_clientid;
	}

	public void setAws_cognito_clientid(String aws_cognito_clientid) {
		this.aws_cognito_clientid = aws_cognito_clientid;
	}

	public String getAws_cognito_client_secret() {
		return aws_cognito_client_secret;
	}

	public void setAws_cognito_client_secret(String aws_cognito_client_secret) {
		this.aws_cognito_client_secret = aws_cognito_client_secret;
	}

	public String getAws_cognito_userpoolid() {
		return aws_cognito_userpoolid;
	}

	public void setAws_cognito_userpoolid(String aws_cognito_userpoolid) {
		this.aws_cognito_userpoolid = aws_cognito_userpoolid;
	}

	public String getAws_jwks() {
		return aws_jwks;
	}

	public void setAws_jwks(String aws_jwks) {
		this.aws_jwks = aws_jwks;
	}

	@Override
	public String toString() {
		return "CredentialsAWS [aws_access_key=" + aws_access_key + ", aws_access_secret=" + aws_access_secret
				+ ", aws_region=" + aws_region + ", aws_cognito_clientid=" + aws_cognito_clientid
				+ ", aws_cognito_client_secret=" + aws_cognito_client_secret + ", aws_cognito_userpoolid="
				+ aws_cognito_userpoolid + ", aws_jwks=" + aws_jwks + "]";
	}
}
