package com.ike.service.banbajio.registerAttendanceUsage.dto;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ike.service.common.dto.identifier;

public class registerAttendance {

	private identifier identifier;

	@Valid
	@JsonProperty("petition")
	private petition3 petition3;

	public identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(identifier identifier) {
		this.identifier = identifier;
	}

	public petition3 getPetition3() {
		return petition3;
	}

	public void setPetition3(petition3 petition3) {
		this.petition3 = petition3;
	}

}
