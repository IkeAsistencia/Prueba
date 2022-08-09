package com.ike.service.cognito.service;

import com.ike.service.cognito.dto.UserSingInRequest;
import com.ike.service.cognito.dto.UserSingInResponse;

public interface ServiceValidToken {

	public boolean validToken(String token);
	public UserSingInResponse getTokenCognito(UserSingInRequest userSingInRequest);
}
