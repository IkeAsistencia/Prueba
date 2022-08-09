package com.ike.service.banbajio.generateInstructionCollection.dto;

import java.io.Serializable;

public class ResultInstructionCollection implements Serializable {

	private static final long serialVersionUID = 1L;
	private String respuestaCobro;
	private String folioIke;
	private CostumerData datosClientes;

	public ResultInstructionCollection() {

	}

	public ResultInstructionCollection(String respuestaCobro, String folioIke, CostumerData datosClientes) {
		super();
		this.respuestaCobro = respuestaCobro;
		this.folioIke = folioIke;
		this.datosClientes = datosClientes;
	}

	public String getFolioIke() {
		return folioIke;
	}

	public void setFolioIke(String folioIke) {
		this.folioIke = folioIke;
	}

	public String getRespuestaCobro() {
		return respuestaCobro;
	}

	public void setRespuestaCobro(String respuestaCobro) {
		this.respuestaCobro = respuestaCobro;
	}

	public CostumerData getDatosClientes() {
		return datosClientes;
	}

	public void setDatosClientes(CostumerData datosClientes) {
		this.datosClientes = datosClientes;
	}

	@Override
	public String toString() {
		return "ResultInstructionCollection [respuestaCobro=" + respuestaCobro + ", folioIke=" + folioIke
				+ ", datosClientes=" + datosClientes + "]";
	}

}
