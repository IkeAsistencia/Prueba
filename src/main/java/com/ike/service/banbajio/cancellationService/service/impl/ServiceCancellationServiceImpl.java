package com.ike.service.banbajio.cancellationService.service.impl;

import java.io.IOException;

import com.ike.service.banbajio.token.dto.GetTokeBB;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ike.service.banbajio.cancellationService.dto.ResponseCancellationService;
import com.ike.service.banbajio.cancellationService.dto.ResultCancellationService;
import com.ike.service.banbajio.cancellationService.dto.cancellation;
import com.ike.service.banbajio.cancellationService.service.ServiceCancellationService;
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
public class ServiceCancellationServiceImpl implements ServiceCancellationService {

	@Value("${cancelacion.servicio.banbajio}")
	private String cancelacion_servicio;
	
	@Value("${api.hub.ambiente}")
	private String api_hub_ambiente;
	
	@Autowired
	ServiceGetToken getToken;

	@Override
	public ResponseCancellationService cancellationService(cancellation cancellation) throws BanBanjioException {
		
		DtoGetToken dtoGetToken = new DtoGetToken();
		OkHttpClient client_http = new OkHttpClient();
		ResponseCancellationService responseCancellationService = new ResponseCancellationService();
		ResultCancellationService resultCancellationService = new ResultCancellationService();
		Response response = null;
		OkHttpsVerifyTLS httpsVerifyTLS = new OkHttpsVerifyTLS();
		JSONObject json_object_body = null;
		JSONObject json_request = new JSONObject();
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

		dtoGetToken = getToken.getToken();
		
		json_request.put("fechaCancelacion", cancellation.getPetition1().getCancellationDate());
		json_request.put("causaCancelacion", cancellation.getPetition1().getCauseCancellation());
		json_request.put("noCliente", cancellation.getPetition1().getCustomerNumber());

		System.out.println("<=== SE QUITO EL OBJECT IDENTIFIER ===>" + json_request);

		if (dtoGetToken.getStatus().equals(200)) {
			System.out.println("<=== IMPLEMENTACION CANCELACION SERVICIO ===>");

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
					                     .url(cancelacion_servicio)
					                     .method("POST", body)
										 .addHeader("Content-Type", "application/json; charset=utf-8")
										 .addHeader("Authorization", "Bearer " + dtoGetToken.getAccess_token())
										 .build();

			System.out.println("<=== REQUEST ===>" + request.toString());
			
			try {
				System.out.println("<=== RESPONSE EXECUTE SERVICE ===>");
				response = client_http.newCall(request).execute();
			} catch (IOException e) {
				System.out.println("<=== ERROR EN LA CONEXION DEL SERVICIO DE CANCELACION DE BANBAJIO ===>");
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
				throw new BanBanjioException(Messages.ERROR_CONEXION_CANCELACION);
			}
			
			String body_responsse = null;
			try {
				body_responsse = response.body().string();
				body_responsse = body_responsse.replace(" ", "");
			} catch (IOException e) {
				System.out.println("<=== ERROR AL OBTENER LA RESPUESTA DEL SERVICIO DE CANCELACION DE BANBAJIO ===>");
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
				throw new BanBanjioException(Messages.ERROR_RESPUESTA_CANCELACION);
			}

			json_object_body = new JSONObject(body_responsse);
			System.out.println("<=== RESPUESTA SERVICE ===>" + json_object_body.toString());
			responseCancellationService.setCodigoRespuesta(json_object_body.getString("codigoRespuesta"));
			responseCancellationService.setMensaje(json_object_body.getString("mensaje"));
			
			if (responseCancellationService.getMensaje().equals("ERROR")) {
				responseCancellationService.setResultadoError((String) json_object_body.get("resultadoError"));
				responseCancellationService.setResultCancellationService(null);
				return responseCancellationService;
			}
			JSONObject json_resultado = json_object_body.getJSONObject("resultado");
			resultCancellationService.setClAfiltmk(json_resultado.getInt("clAfiltmk"));
			resultCancellationService.setFolioCancelacion(json_resultado.getInt("folioCancelacion"));
			responseCancellationService.setResultCancellationService(resultCancellationService);
			return responseCancellationService;
		}
		return responseCancellationService;
	}
}
