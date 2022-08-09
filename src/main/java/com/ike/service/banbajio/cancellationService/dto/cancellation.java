package com.ike.service.banbajio.cancellationService.dto;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ike.service.common.dto.identifier;

public class cancellation {

	private identifier identifier;
	
	@Valid
	@JsonProperty("petition")
	private petition1 petition1;

	public identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(identifier identifier) {
		this.identifier = identifier;
	}

	public petition1 getPetition1() {
		return petition1;
	}

	public void setPetition1(petition1 petition1) {
		this.petition1 = petition1;
	}

}
