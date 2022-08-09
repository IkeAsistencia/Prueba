package com.ike.service.banbajio.getCustomersProspects.dto;

import java.util.List;

public class ResponseProspects {

	private String codigoRespuesta;
	private String mensaje;
	private String resultadoError;
	private List<ResultProspects> resultado;

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

	public List<ResultProspects> getResultado() {
		return resultado;
	}

	public void setResultado(List<ResultProspects> resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "ResponseProspects [codigoRespuesta=" + codigoRespuesta + ", mensaje=" + mensaje + ", resultadoError="
				+ resultadoError + ", resultado=" + resultado + "]";
	}

}
