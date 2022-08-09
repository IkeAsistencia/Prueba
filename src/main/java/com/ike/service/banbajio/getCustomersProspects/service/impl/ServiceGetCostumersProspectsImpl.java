package com.ike.service.banbajio.getCustomersProspects.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ike.service.banbajio.token.dto.GetTokeBB;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ike.service.banbajio.getCustomersProspects.dto.CardProspects;
import com.ike.service.banbajio.getCustomersProspects.dto.ResponseProspects;
import com.ike.service.banbajio.getCustomersProspects.dto.ResultProspects;
import com.ike.service.banbajio.getCustomersProspects.service.ServiceGetCostumersProspects;
import com.ike.service.banbajio.token.dto.DtoGetToken;
import com.ike.service.banbajio.token.service.ServiceGetToken;
import com.ike.service.common.constants.Messages;
import com.ike.service.common.exceptions.BanBanjioException;
import com.ike.service.common.security.OkHttpsVerifyTLS;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class ServiceGetCostumersProspectsImpl implements ServiceGetCostumersProspects {

	@Value("${clientes.prospectos.banbajio}")
	private String service_clientes_prospectos;
	
	@Value("${api.hub.ambiente}")
	private String api_hub_ambiente;
	
	@Autowired
	ServiceGetToken serviceToken;

	@Override
	public ResponseProspects getCostumersProspects() throws BanBanjioException {

		ResponseProspects responseProspects = null;
		DtoGetToken dtoGetToken = new DtoGetToken();
		ResultProspects resultProspects = new ResultProspects(); 
		CardProspects cardProspects = new CardProspects();
		JSONObject json_object_body = null;
		OkHttpClient client_http = new OkHttpClient();
		Response response = null;
		OkHttpsVerifyTLS httpsVerifyTLS = new OkHttpsVerifyTLS();
		List<ResultProspects> listRequestProspect = new ArrayList<>();
		List<CardProspects> listCardProspects = new ArrayList<>();
		
		dtoGetToken = serviceToken.getToken();

		if (dtoGetToken.getStatus().equals(200)) {
			System.out.println("<=== IMPLEMENTACION GET POSPECTOS CLIENTES ===>");
			try {
				client_http = httpsVerifyTLS.getSocketFactory();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
			}
			
			Request request = new Request.Builder()
					                     .url(service_clientes_prospectos)
					                     .get()
					                     .addHeader("Content-Type", "application/json; charset=utf-8")
					                     .addHeader("Authorization", "Bearer " + dtoGetToken.getAccess_token())
					                     .build();
			System.out.println("<=== REQUEST ===>" + request.toString());			
			try {
				System.out.println("<=== RESPONSE ===>");
				response = client_http.newCall(request).execute();
			} catch (IOException e) {
				System.out.println("<=== ERROR EN LA CONEXION DEL SERVICIO DE OBTENER PROSPECTOS DE CLIENTES DE BANBAJIO ===>");
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
				throw new BanBanjioException(Messages.ERROR_CONEXION_OBTENER_CLIENTES);
			}
			
			try {
				responseProspects = new ResponseProspects();
				
				String object_json = response.body().string();
				System.out.println("<=== RESPUESTA SERVICE ===>");				
				json_object_body =  new JSONObject(object_json);
				responseProspects.setCodigoRespuesta((String) json_object_body.get("codigoRespuesta"));
				responseProspects.setMensaje((String) json_object_body.get("mensaje"));
				
				if (responseProspects.getMensaje().equals("ERROR")) {
					responseProspects.setResultadoError((String) json_object_body.get("resultadoError"));
					responseProspects.setResultado(null);
					return responseProspects;
				}
				
				for (int i = 0; i < json_object_body.getJSONArray("resultado").length(); i++) {
					resultProspects = new ResultProspects();
					listCardProspects = new ArrayList<>();
					JSONObject json_object = (JSONObject) json_object_body.getJSONArray("resultado").get(i);
					resultProspects.setNoClienteUnico(json_object.getInt("noClienteUnico"));
					resultProspects.setNombre(json_object.getString("nombre"));
					resultProspects.setEmpleado(json_object.getBoolean("empleado"));
					resultProspects.setGenero((String) json_object.get("genero"));
					resultProspects.setEmail((String) json_object.get("email"));
					resultProspects.setRfc((String) json_object.get("rfc"));
					resultProspects.setSucursal((String) json_object.get("sucursal"));
					resultProspects.setEstado((String) json_object.get("estado"));
					resultProspects.setFechaNacimiento((String) json_object.get("fechaNacimiento"));
					resultProspects.setTelefonoCasa((String) json_object.get("telefonoCasa"));
					resultProspects.setTelefonoCelular((String) json_object.get("telefonoCelular"));
					resultProspects.setTelefonoOficina((String) json_object.get("telefonoOficina"));
					resultProspects.setFechaAlta((String) json_object.get("fechaAlta"));

					if (json_object.getJSONArray("tarjetas").isEmpty()) {
						for(int j = 0; j < json_object.getJSONArray("tarjetas").length(); j++){
							cardProspects = new CardProspects();
							JSONObject jsonObjectCard = (JSONObject) json_object.getJSONArray("tarjetas").get(j);
							cardProspects.setTdc((jsonObjectCard.getString("tdc") == null) ? "" : jsonObjectCard.getString("tdc"));
							cardProspects.setTdd((jsonObjectCard.getString("tdd") == null) ? "" : jsonObjectCard.getString("tdc"));
							listCardProspects.add(cardProspects);
						}
						System.out.println("<=== TARJETAS ===>");
						System.out.println(listCardProspects);
					}
				
					resultProspects.setTarjetas(listCardProspects);
					listRequestProspect.add(resultProspects);
					responseProspects.setResultado(listRequestProspect);
				}
				return responseProspects;
			} catch (IOException e) {
				System.out.println("<=== ERROR AL OBTENER LA RESPUESTA DEL SERVICIO DE OBTENER PROSPECTOS DE BANBAJIO ===>");
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
				throw new BanBanjioException(Messages.ERROR_RESPUESTA_OBTENER_CLIENTES);
			}
		}
		return responseProspects;
	}
}
