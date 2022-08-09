package com.ike.service.banbajio.registerAttendanceUsage.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class petition3 {

	private Integer customerNumber;
	private String fileAssistanceRequest;

	@Length(max = 20, message = "El Formato de la fecha no es el correcto yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String dateAssistanceRequest;

	@NotBlank(message = "client Name cannot be empty")
	private String clientName;

	@NotBlank(message = "assistanc eRequest Status cannot be empty")
	private String assistanceRequestStatus;

	@NotBlank(message = "state cannot be empty")
	private String state;

	@NotBlank(message = "city cannot be empty")
	private String city;

	@NotBlank(message = "service cannot be empty")
	private String service;

	@NotBlank(message = "type Service cannot be empty")
	private String typeService;

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getFileAssistanceRequest() {
		return fileAssistanceRequest;
	}

	public void setFileAssistanceRequest(String fileAssistanceRequest) {
		this.fileAssistanceRequest = fileAssistanceRequest;
	}

	public String getDateAssistanceRequest() {
		return dateAssistanceRequest;
	}

	public void setDateAssistanceRequest(String dateAssistanceRequest) {
		this.dateAssistanceRequest = dateAssistanceRequest;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAssistanceRequestStatus() {
		return assistanceRequestStatus;
	}

	public void setAssistanceRequestStatus(String assistanceRequestStatus) {
		this.assistanceRequestStatus = assistanceRequestStatus;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTypeService() {
		return typeService;
	}

	public void setTypeService(String typeService) {
		this.typeService = typeService;
	}

	@Override
	public String toString() {
		return "petition3 [customerNumber=" + customerNumber + ", fileAssistanceRequest=" + fileAssistanceRequest
				+ ", dateAssistanceRequest=" + dateAssistanceRequest + ", clientName=" + clientName
				+ ", assistanceRequestStatus=" + assistanceRequestStatus + ", state=" + state + ", city=" + city
				+ ", service=" + service + ", typeService=" + typeService + "]";
	}

}
