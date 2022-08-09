package com.ike.service.banbajio.token.dto;

import java.io.Serializable;

public class DtoGetToken implements Serializable {

	private static final long serialVersionUID = 1L;

	private String access_token;
	private Integer expires_in;
	private String token_type;
	private String scope;
	private Integer status;

	public DtoGetToken() {

	}

	public DtoGetToken(String access_token, Integer expires_in, String token_type, String scope, Integer status) {
		super();
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.token_type = token_type;
		this.scope = scope;
		this.status = status;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DtoGetToken [access_token=" + access_token + ", expires_in=" + expires_in + ", token_type=" + token_type
				+ ", scope=" + scope + ", status=" + status + "]";
	}

}
