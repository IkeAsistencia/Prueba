package com.ike.service.banbajio.generateInstructionCollection.dto;

import java.io.Serializable;

public class CostumerData implements Serializable {

	private static final long serialVersionUID = 1L;
	private String domicilioCompleto;
	private Integer noCliente;

	public CostumerData() {

	}

	public CostumerData(String domicilioCompleto, Integer noCliente) {
		this.domicilioCompleto = domicilioCompleto;
		this.noCliente = noCliente;
	}

	public String getDomicilioCompleto() {
		return domicilioCompleto;
	}

	public void setDomicilioCompleto(String domicilioCompleto) {
		this.domicilioCompleto = domicilioCompleto;
	}

	public Integer getNoCliente() {
		return noCliente;
	}

	public void setNoCliente(Integer noCliente) {
		this.noCliente = noCliente;
	}

	@Override
	public String toString() {
		return "CostumerData [domicilioCompleto=" + domicilioCompleto + ", noCliente=" + noCliente + "]";
	}

}
