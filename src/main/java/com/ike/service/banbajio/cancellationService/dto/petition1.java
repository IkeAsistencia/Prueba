package com.ike.service.banbajio.cancellationService.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class petition1 {

	//El Formato de la fecha no es el correcto
	@NotBlank(message = "cancellation Date cannot be empty")
	@Length(max = 20, message = "The date format is not correct yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String cancellationDate;

	//La causa de cancelación, no puede estar vacío
	@NotBlank(message = "The cause of cancellation cannot be empty")
	private String causeCancellation;

	//El numero del cliente no puede estar vacío
	@NotNull(message = "Customer number cannot be empty")
	private Integer customerNumber;

	public String getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(String cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public String getCauseCancellation() {
		return causeCancellation;
	}

	public void setCauseCancellation(String causeCancellation) {
		this.causeCancellation = causeCancellation;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Override
	public String toString() {
		return "petition1 [cancellationDate=" + cancellationDate + ", causeCancellation=" + causeCancellation
				+ ", customerNumber=" + customerNumber + "]";
	}

}
