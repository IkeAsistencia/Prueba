package com.ike.service.banbajio.token.service.impl;

import java.io.IOException;

import com.ike.service.banbajio.token.dto.GetTokeBB;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
public class ServiceGetTokenImpl implements ServiceGetToken {

	@Value("${token.banbajio}")
	private String service_token_banbajio;
	
	@Value("${content.banbajio}")
	private String content_banbajio;
	
	@Value("${api.hub.ambiente}")
	private String api_hub_ambiente;
	
	@Override
	public DtoGetToken getToken() throws BanBanjioException {
		
		OkHttpsVerifyTLS httpsVerifyTLS = new OkHttpsVerifyTLS();
		DtoGetToken dtoGetToken = new DtoGetToken();
		OkHttpClient client_http = new OkHttpClient();
		Response response = null;
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		String content = content_banbajio;
		System.out.println("<=== IMPLEMENTACION TOKEN ===>");
        try {
			client_http = httpsVerifyTLS.getSocketFactory();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
		}
				
		@SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(mediaType, content);	
		Request request = new Request.Builder()
				                     .url(service_token_banbajio)
				                     .method("POST", body)
				                     .addHeader("Content-Type", "application/x-www-form-urlencoded")
				                     .build();
		System.out.println("<=== REQUEST ===>" + request);
		int intentos = 1;
		while(intentos < 3) {
			try {
				System.out.println("Numero de intento: " + intentos);
				System.out.println("<=== RESPONSE EXECUTE SERVICE ===>");
				response = client_http.newCall(request).execute();
				break;
			} catch (IOException e) {
				System.out.println("<=== ERROR EN LA CONEXION DEL SERVICIO DE GENERAR TOKEN DE BANBAJIO ===>");
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				//throw new BanBanjioException(Messages.ERROR_CONEXION_TOKEN);
			}
			intentos++;
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e){
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
			}
		}

		String body_responsse = null;
		try {
			body_responsse = response.body().string();
			body_responsse = body_responsse.replace(" ", "");
			System.out.println("<=== RESPUESTA SERVICE ===>" + body_responsse);
		} catch (IOException e) {
			System.out.println("<=== ERROR AL OBTENER LA RESPUESTA DEL SERVICIO DE GENERAR TOKEN DE BANBAJIO ===>");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
			throw new BanBanjioException(Messages.ERROR_RESPUESTA_TOKEN);
		}
		
		JSONObject json_object_body = new JSONObject(body_responsse);
		dtoGetToken.setAccess_token((String) json_object_body.get("access_token"));
		dtoGetToken.setToken_type((String) json_object_body.get("token_type"));
		dtoGetToken.setScope((String) json_object_body.get("scope"));
		dtoGetToken.setStatus(200);
		GetTokeBB.setStatus(200);
		System.out.println("<=== OBJECT DTO ===> " + dtoGetToken);
		return dtoGetToken;
	}
}
