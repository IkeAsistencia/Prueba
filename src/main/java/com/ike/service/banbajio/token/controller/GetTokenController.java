package com.ike.service.banbajio.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.ike.service.banbajio.token.dto.DtoGetToken;
import com.ike.service.banbajio.token.service.ServiceGetToken;
import com.ike.service.common.exceptions.BanBanjioException;


public class GetTokenController {

	@Autowired
	ServiceGetToken serviceGetToken;

	@GetMapping
	public ResponseEntity<Object> getToken() {
		DtoGetToken dtoGetToken = new DtoGetToken();
		try {
			dtoGetToken = serviceGetToken.getToken();
			if (dtoGetToken.getStatus().equals(200)) {
				return new ResponseEntity<>(dtoGetToken, HttpStatus.OK);
			}
			if (dtoGetToken.getStatus().equals(400)) {
				return new ResponseEntity<>(dtoGetToken, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(dtoGetToken, HttpStatus.BAD_REQUEST);
		} catch (BanBanjioException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
