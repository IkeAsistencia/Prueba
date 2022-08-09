package com.ike.service.banbajio.registerAttendanceUsage.dto;

import java.io.Serializable;

public class ResponseRegisterAttendance implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoRespuesta;
	private String mensaje;
	private String resultadoError;
	private ResultRegisterAttendance resultado;

	public ResponseRegisterAttendance() {

	}

	public ResponseRegisterAttendance(String codigoRespuesta, String mensaje, String resultadoError,
			ResultRegisterAttendance resultado) {
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

	public ResultRegisterAttendance getResultado() {
		return resultado;
	}

	public void setResultado(ResultRegisterAttendance resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "ResponseRegisterAttendance [codigoRespuesta=" + codigoRespuesta + ", mensaje=" + mensaje
				+ ", resultadoError=" + resultadoError + ", resultado=" + resultado + "]";
	}

}
