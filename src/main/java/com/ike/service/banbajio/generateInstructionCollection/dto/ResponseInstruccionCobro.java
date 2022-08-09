package com.ike.service.banbajio.generateInstructionCollection.dto;

import java.io.Serializable;

public class ResponseInstruccionCobro implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoRespuesta;
	private String mensaje;
	private String resultadoError;
	private ResultInstructionCollection resultado;

	public ResponseInstruccionCobro() {

	}

	public ResponseInstruccionCobro(String codigoRespuesta, String mensaje, String resultadoError,
			ResultInstructionCollection resultado) {
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

	public ResultInstructionCollection getResultado() {
		return resultado;
	}

	public void setResultado(ResultInstructionCollection resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "ResponseInstruccionCobro [codigoRespuesta=" + codigoRespuesta + ", mensaje=" + mensaje
				+ ", resultadoError=" + resultadoError + ", resultado=" + resultado + "]";
	}

}
