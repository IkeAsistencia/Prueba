package com.ike.service.banbajio.generateInstructionCollection.dto;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ike.service.common.dto.identifier;

public class generateInstruction {

	private identifier identifier;
	
	@Valid
	@JsonProperty("petition")
	private petition2 petition2;

	public identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(identifier identifier) {
		this.identifier = identifier;
	}

	public petition2 getPetition2() {
		return petition2;
	}

	public void setPetition2(petition2 petition2) {
		this.petition2 = petition2;
	}

	
}
