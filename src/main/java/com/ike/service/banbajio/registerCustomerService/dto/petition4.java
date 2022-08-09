package com.ike.service.banbajio.registerCustomerService.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class petition4 {
	
	//@NotBlank(message = "package Contracted cannot be empty")
	private String packageContracted;
	
	//@NotBlank(message = "clAfiltmk cannot be empty")
	private String clAfiltmk;
	
	//@NotBlank(message = "response Code be empty")
	private String responseCode;
	
	//@NotBlank(message = "reason cannot be empty")
	private String reason;
	
	//@NotBlank(message = "result cannot be empty")
	private String result;
	
	//@NotNull(message = "number Calls cannot be empty")
	private Integer numberCalls;
	
	//@NotBlank(message = "telCc cannot be empty")
	private String telCc;
	
	//@NotBlank(message = "comments cannot be empty")
	private String comments;
	
	//@NotNull(message = "Customer number cannot be empty")
	private Integer customerNumber;
	
	//@NotBlank(message = "date Recruitment cannot be empty")
	//@Length(max = 20, message = "El Formato de la fecha no es el correcto yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String dateRecruitment;
	
	private boolean confirmedSale;
	private boolean noOfrecerServ;
	private String dateTimeQuote;
	private String dateTimeManagement;
	private String dateRejectionService;
	private String detail;
	private String levelOffered;
	
	public boolean isConfirmedSale() {
		return confirmedSale;
	}

	public void setConfirmedSale(boolean confirmedSale) {
		this.confirmedSale = confirmedSale;
	}

	public String getPackageContracted() {
		return packageContracted;
	}

	public void setPackageContracted(String packageContracted) {
		this.packageContracted = packageContracted;
	}

	public String getClAfiltmk() {
		return clAfiltmk;
	}

	public void setClAfiltmk(String clAfiltmk) {
		this.clAfiltmk = clAfiltmk;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getLevelOffered() {
		return levelOffered;
	}

	public void setLevelOffered(String levelOffered) {
		this.levelOffered = levelOffered;
	}

	public Integer getNumberCalls() {
		return numberCalls;
	}

	public void setNumberCalls(Integer numberCalls) {
		this.numberCalls = numberCalls;
	}

	public String getTelCc() {
		return telCc;
	}

	public void setTelCc(String telCc) {
		this.telCc = telCc;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isNoOfrecerServ() {
		return noOfrecerServ;
	}

	public void setNoOfrecerServ(boolean noOfrecerServ) {
		this.noOfrecerServ = noOfrecerServ;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getDateTimeQuote() {
		return dateTimeQuote;
	}

	public void setDateTimeQuote(String dateTimeQuote) {
		this.dateTimeQuote = dateTimeQuote;
	}

	public String getDateTimeManagement() {
		return dateTimeManagement;
	}

	public void setDateTimeManagement(String dateTimeManagement) {
		this.dateTimeManagement = dateTimeManagement;
	}

	public String getDateRecruitment() {
		return dateRecruitment;
	}

	public void setDateRecruitment(String dateRecruitment) {
		this.dateRecruitment = dateRecruitment;
	}

	public String getDateRejectionService() {
		return dateRejectionService;
	}

	public void setDateRejectionService(String dateRejectionService) {
		this.dateRejectionService = dateRejectionService;
	}

}
