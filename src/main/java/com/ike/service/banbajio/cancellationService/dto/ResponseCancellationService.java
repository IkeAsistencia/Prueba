package com.ike.service.banbajio.cancellationService.dto;

import java.io.Serializable;

public class ResponseCancellationService implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoRespuesta;
	private String mensaje;
	private String resultadoError;
	private ResultCancellationService resultado;

	public ResponseCancellationService() {

	}

	public ResponseCancellationService(String codigoRespuesta, String mensaje, String resultadoError,
			ResultCancellationService resultado) {
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

	public ResultCancellationService getResultCancellationService() {
		return resultado;
	}

	public void setResultCancellationService(ResultCancellationService resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "ResponseCancellationService [codigoRespuesta=" + codigoRespuesta + ", mensaje=" + mensaje
				+ ", resultadoError=" + resultadoError + ", resultado=" + resultado + "]";
	}

}
