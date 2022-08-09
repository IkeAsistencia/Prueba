package com.ike.service.banbajio.registerCustomerService.dto;

import java.io.Serializable;

public class ResponseRegisterCustomer implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoRespuesta;
	private String mensaje;
	private String resultadoError;
	private ResultRegisterCustomer resultado;

	public ResponseRegisterCustomer() {

	}

	public ResponseRegisterCustomer(String codigoRespuesta, String mensaje, String resultadoError,
			ResultRegisterCustomer resultado) {
		this.codigoRespuesta = codigoRespuesta;
		this.mensaje = mensaje;
		this.resultadoError = resultadoError;
		this.resultado = resultado;
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getResultadoError() {
		return resultadoError;
	}

	public void setResultadoError(String resultadoError) {
		this.resultadoError = resultadoError;
	}

	public ResultRegisterCustomer getResultado() {
		return resultado;
	}

	public void setResultado(ResultRegisterCustomer resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "ResponseRegisterCustomer [codigoRespuesta=" + codigoRespuesta + ", mensaje=" + mensaje
				+ ", resultadoError=" + resultadoError + ", resultado=" + resultado + "]";
	}

}
