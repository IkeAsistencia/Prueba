package com.ike.service.banbajio.registerCustomerService.service.impl;

import java.io.IOException;

import com.ike.service.banbajio.token.dto.GetTokeBB;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ike.service.banbajio.registerCustomerService.dto.ResponseRegisterCustomer;
import com.ike.service.banbajio.registerCustomerService.dto.ResultRegisterCustomer;
import com.ike.service.banbajio.registerCustomerService.dto.registerCustomer;
import com.ike.service.banbajio.registerCustomerService.service.ServiceRegisterCustomerService;
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
public class ServiceRegisterCustomerServiceImpl implements ServiceRegisterCustomerService {

	@Autowired
	ServiceGetToken getToken;

	@Value("${registrar.servicio.cliente.banbajio}")
	private String service_registrar_servicio_cliente;
	
	@Value("${api.hub.ambiente}")
	private String api_hub_ambiente;

	@Override
	public ResponseRegisterCustomer registerCustomerService(registerCustomer registerCustomer) throws BanBanjioException {
		
		DtoGetToken dtoGetToken = new DtoGetToken();
		ResponseRegisterCustomer responseRegisterCustomer = new ResponseRegisterCustomer();
		ResultRegisterCustomer resultRegisterCustomer = new ResultRegisterCustomer();
		JSONObject json_request = new JSONObject();
		OkHttpClient client_http = new OkHttpClient();
		Response response = null;
		OkHttpsVerifyTLS httpsVerifyTLS = new OkHttpsVerifyTLS();
		
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

		dtoGetToken = getToken.getToken();

		json_request.put("ventaConfirmada", registerCustomer.getPetition4().isConfirmedSale());
		json_request.put("paqueteContratado", registerCustomer.getPetition4().getPackageContracted());
		json_request.put("clAfiltmk", registerCustomer.getPetition4().getClAfiltmk());
		json_request.put("codigoRespuesta", registerCustomer.getPetition4().getResponseCode());
		json_request.put("motivo", registerCustomer.getPetition4().getReason());
		json_request.put("resultado", registerCustomer.getPetition4().getResult());
		json_request.put("detalle", registerCustomer.getPetition4().getDetail());
		json_request.put("nivelOfrecido", registerCustomer.getPetition4().getLevelOffered());
		json_request.put("numLlamadas", registerCustomer.getPetition4().getNumberCalls());
		json_request.put("telCc", registerCustomer.getPetition4().getTelCc());
		json_request.put("comentarios", registerCustomer.getPetition4().getComments());
		json_request.put("noOfrecerServ", registerCustomer.getPetition4().isNoOfrecerServ());
		json_request.put("noCliente", registerCustomer.getPetition4().getCustomerNumber());
		json_request.put("fechaHoraCita", registerCustomer.getPetition4().getDateTimeQuote());
		json_request.put("fechaHoraGestion", registerCustomer.getPetition4().getDateTimeManagement());
		json_request.put("fechaContratacion", registerCustomer.getPetition4().getDateRecruitment());
		json_request.put("fechaRechazoServicio", registerCustomer.getPetition4().getDateRejectionService());

		System.out.println("<=== SE QUITO EL OBJECT IDENTIFIER ===>" + json_request);

		if (dtoGetToken.getStatus().equals(200)) {
			System.out.println("<=== IMPLEMENTACION REGISTRAR SERVICIO CLIENTE ===>");
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
					                     .url(service_registrar_servicio_cliente)
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
			
			JSONObject json_object_body = new JSONObject(body_responsse);
			System.out.println("<=== RESPUESTA SERVICE ===>" + json_object_body.toString());
			responseRegisterCustomer.setCodigoRespuesta(json_object_body.getString("codigoRespuesta"));
			responseRegisterCustomer.setMensaje(json_object_body.getString("mensaje"));
			
			if (responseRegisterCustomer.getMensaje().equals("ERROR")) {
				responseRegisterCustomer.setResultadoError((String) json_object_body.get("resultadoError"));
				responseRegisterCustomer.setResultado(null);
				return responseRegisterCustomer;
			}

			responseRegisterCustomer.setResultadoError(null);
			JSONObject json_resultado = json_object_body.getJSONObject("resultado");
			resultRegisterCustomer.setFolioServicio(json_resultado.getInt("folioServicio"));
			resultRegisterCustomer.setClAfiltmk(json_resultado.getString("clafilmk"));
			responseRegisterCustomer.setResultado(resultRegisterCustomer);
			System.out.println("<=== OBJECT DTO ===>" + resultRegisterCustomer);
			return responseRegisterCustomer;
		}
		return responseRegisterCustomer;
	}
}
