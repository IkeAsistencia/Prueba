package com.ike.service.cognito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ike.service.cognito.dto.UserSingInRequest;
import com.ike.service.cognito.dto.UserSingInResponse;
import com.ike.service.cognito.service.ServiceValidToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/cognito")
@Api(description = "Service to generate Cognito tokens, controlled by the API HUB middleware.", tags = "Cognito API' HUB")
public class CognitoController {

	@Autowired ServiceValidToken serviceValidToken; 
	
	@ApiOperation(value = "Generate Token")
	@PostMapping(value = "/token")
	public ResponseEntity<Object> getTokenCognito(@Validated @RequestBody UserSingInRequest userSingInRequest){
		UserSingInResponse userSingInResponse = new UserSingInResponse();
		userSingInResponse = serviceValidToken.getTokenCognito(userSingInRequest);
		
		return new ResponseEntity<>(userSingInResponse, HttpStatus.OK);
	}
}
