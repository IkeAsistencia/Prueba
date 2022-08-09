package com.ike.service.banbajio.registerCustomerService.dto;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ike.service.common.dto.identifier;

public class registerCustomer {

	private identifier identifier;

	@Valid
	@JsonProperty("petition")
	private petition4 petition4;

	public identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(identifier identifier) {
		this.identifier = identifier;
	}

	public petition4 getPetition4() {
		return petition4;
	}

	public void setPetition4(petition4 petition4) {
		this.petition4 = petition4;
	}

}
