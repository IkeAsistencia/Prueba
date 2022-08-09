package com.ike.service.banbajio.registerCustomerService.dto;

import java.io.Serializable;

public class ResultRegisterCustomer implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer folioServicio;
	private String clAfiltmk;

	public ResultRegisterCustomer() {

	}

	public ResultRegisterCustomer(Integer folioServicio, String clAfiltmk) {
		super();
		this.folioServicio = folioServicio;
		this.clAfiltmk = clAfiltmk;
	}

	public Integer getFolioServicio() {
		return folioServicio;
	}

	public void setFolioServicio(Integer folioServicio) {
		this.folioServicio = folioServicio;
	}

	public String getClAfiltmk() {
		return clAfiltmk;
	}

	public void setClAfiltmk(String clAfiltmk) {
		this.clAfiltmk = clAfiltmk;
	}

	@Override
	public String toString() {
		return "ResultRegisterCustomer [folioServicio=" + folioServicio + ", clAfiltmk=" + clAfiltmk + "]";
	}

}
