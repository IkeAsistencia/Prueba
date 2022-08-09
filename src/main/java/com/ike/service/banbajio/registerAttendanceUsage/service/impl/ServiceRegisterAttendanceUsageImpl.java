package com.ike.service.banbajio.registerAttendanceUsage.service.impl;

import java.io.IOException;

import com.ike.service.banbajio.token.dto.GetTokeBB;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ike.service.banbajio.registerAttendanceUsage.dto.ResponseRegisterAttendance;
import com.ike.service.banbajio.registerAttendanceUsage.dto.ResultRegisterAttendance;
import com.ike.service.banbajio.registerAttendanceUsage.dto.registerAttendance;
import com.ike.service.banbajio.registerAttendanceUsage.service.ServiceRegisterAttendanceUsage;
import com.ike.service.banbajio.token.dto.DtoGetToken;
import com.ike.service.banbajio.token.service.ServiceGetToken;
import com.ike.service.common.constants.Messages;
import com.ike.service.common.exceptions.BanBanjioException;
import com.ike.service.common.security.OkHttpsVerifyTLS;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class ServiceRegisterAttendanceUsageImpl implements ServiceRegisterAttendanceUsage {

	@Autowired
	ServiceGetToken getToken;

	@Value("${registrar.uso.asistencia.banbajio}")
	private String service_registrar_uso_asistencia;
	
	@Value("${api.hub.ambiente}")
	private String api_hub_ambiente;

	@Override
	public ResponseRegisterAttendance registerAttendanceUsage(registerAttendance registerAttendance) throws BanBanjioException {
		
		DtoGetToken dtoGetToken = new DtoGetToken();
		ResultRegisterAttendance resultRegisterAttendance = new ResultRegisterAttendance();
		ResponseRegisterAttendance responseRegisterAttendance = new ResponseRegisterAttendance();
		Response response = null;
		OkHttpsVerifyTLS httpsVerifyTLS = new OkHttpsVerifyTLS();
		JSONObject json_request = new JSONObject();
		JSONObject json_object_body = null;
		OkHttpClient client_http = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		
		dtoGetToken = getToken.getToken();

		json_request.put("NoCliente", registerAttendance.getPetition3().getCustomerNumber());
		json_request.put("ExpedientePeticionAsistencia", registerAttendance.getPetition3().getFileAssistanceRequest());
		json_request.put("FechaPeticionAsistencia", registerAttendance.getPetition3().getDateAssistanceRequest());
		json_request.put("NombreCliente", registerAttendance.getPetition3().getClientName());
		json_request.put("EstatusPeticionAsistencia", registerAttendance.getPetition3().getAssistanceRequestStatus());
		json_request.put("Estado", registerAttendance.getPetition3().getState());
		json_request.put("Ciudad", registerAttendance.getPetition3().getCity());
		json_request.put("Servicio", registerAttendance.getPetition3().getService());
		json_request.put("TipoServicio", registerAttendance.getPetition3().getTypeService());

		System.out.println("<=== SE QUITO EL OBJECT IDENTIFIER ===>" + json_request);

		if (dtoGetToken.getStatus().equals(200)) {
			
			System.out.println("<=== IMPLEMENTACION REGISTRAR USO ASISTENCIA ===>");

			try {
				client_http = httpsVerifyTLS.getSocketFactory();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
			}
			
			@SuppressWarnings("deprecation")
			RequestBody body = RequestBody.create(mediaType, json_request.toString());
			Request request = new Request.Builder()
					                     .url(service_registrar_uso_asistencia)
					                     .method("POST", body)
										 .addHeader("Content-Type", "application/json; charset=utf-8")
										 .addHeader("Authorization", "Bearer " + dtoGetToken.getAccess_token())
										 .build();
			System.out.println("<=== REQUEST ===>" + request.toString());
			try {
				System.out.println("<=== RESPONSE EXECUTE SERVICE ===>");
				response = client_http.newCall(request).execute();
			} catch (IOException e) {
				System.out.println("<=== ERROR EN LA CONEXION DEL SERVICIO DE REGISTRO USO ASISTENCIA DE BANBAJIO ===>");
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
				throw new BanBanjioException(Messages.ERROR_CONEXION_REGISTRO_ASISTENCIA);
			}
			
			String body_responsse = null;
			try {
				body_responsse = response.body().string();
				body_responsse = body_responsse.replace(" ", "");
			} catch (IOException e) {
				System.out.println("<=== ERROR AL OBTENER LA RESPUESTA DEL SERVICIO DE REGISTRO USO ASISTENCIA DE BANBAJIO ===>");
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
				throw new BanBanjioException(Messages.ERROR_RESPUESTA_REGISTRO_ASISTENCIA);
			}
			
			json_object_body = new JSONObject(body_responsse);
			System.out.println("<=== RESPUESTA SERVICE ===>" + json_object_body.toString());
			responseRegisterAttendance.setCodigoRespuesta(json_object_body.getString("codigoRespuesta"));
			responseRegisterAttendance.setMensaje(json_object_body.getString("mensaje"));
			
			if (responseRegisterAttendance.getMensaje().equals("ERROR")) {
				responseRegisterAttendance.setResultadoError((String) json_object_body.get("resultadoError"));
				responseRegisterAttendance.setResultado(null);
				return responseRegisterAttendance;
			}

			responseRegisterAttendance.setResultadoError(null);
			JSONObject json_resultado = json_object_body.getJSONObject("resultado");
			resultRegisterAttendance.setFolioServicio(json_resultado.getInt("folioServicio"));
			resultRegisterAttendance.setClAfiltmk(json_resultado.getString("clafilmk"));
			responseRegisterAttendance.setResultado(resultRegisterAttendance);
			System.out.println("<=== OBJECT DTO ===>" + responseRegisterAttendance);
			return responseRegisterAttendance;
		}
		return responseRegisterAttendance;
	}
}
