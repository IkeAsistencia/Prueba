package com.ike.service.banbajio.generateInstructionCollection.service.impl;

import java.io.IOException;

import com.ike.service.banbajio.token.dto.GetTokeBB;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ike.service.banbajio.generateInstructionCollection.dto.CostumerData;
import com.ike.service.banbajio.generateInstructionCollection.dto.ResponseInstruccionCobro;
import com.ike.service.banbajio.generateInstructionCollection.dto.ResultInstructionCollection;
import com.ike.service.banbajio.generateInstructionCollection.dto.generateInstruction;
import com.ike.service.banbajio.generateInstructionCollection.service.ServiceGenerateInstructionCollection;
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
public class ServiceGenerateInstructionCollectionImpl implements ServiceGenerateInstructionCollection {

	@Autowired
	ServiceGetToken getToken;

	@Value("${generar.instruccion.cobro.banbajio}")
	private String service_generar_instruccion_cobro;
	
	@Value("${api.hub.ambiente}")
	private String api_hub_ambiente;

	@Override
	public ResponseInstruccionCobro generateInstructionCollection(generateInstruction generateInstruction) throws BanBanjioException {
		
		DtoGetToken dtoGetToken = new DtoGetToken();
		ResultInstructionCollection resultInstructionCollection = new ResultInstructionCollection();
		ResponseInstruccionCobro responseInstruccionCobro = new ResponseInstruccionCobro();
		CostumerData costumerData = new CostumerData();
		OkHttpClient client_http = new OkHttpClient();
		JSONObject json_object_body = null;
		Response response = null;
		OkHttpsVerifyTLS httpsVerifyTLS = new OkHttpsVerifyTLS();
		JSONObject json_request = new JSONObject();
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

		dtoGetToken = getToken.getToken();

		json_request.put("TipoCobro", generateInstruction.getPetition2().getTypeCharge());
		json_request.put("NoCliente", generateInstruction.getPetition2().getNumberCustomer());
		json_request.put("clAfiltmk", generateInstruction.getPetition2().getClAfiltmk());
		json_request.put("producto", generateInstruction.getPetition2().getProduct());
		json_request.put("subproducto", generateInstruction.getPetition2().getByProduct());
		json_request.put("tarjeta", generateInstruction.getPetition2().getCard());
		json_request.put("paquete", generateInstruction.getPetition2().getPacket());
		json_request.put("descripcion", generateInstruction.getPetition2().getDescription());
		json_request.put("gratuito", generateInstruction.getPetition2().isGratuitous());
		json_request.put("comentarios", generateInstruction.getPetition2().getComments());

		System.out.println("<=== SE QUITO EL OBJECT IDENTIFIER ===>" + json_request);

		if (dtoGetToken.getStatus().equals(200)) {
			
			System.out.println("<=== IMPLEMENTACION GENERAR INSTRUCCION DE COBRO ===>");
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
					                     .url(service_generar_instruccion_cobro)
					                     .method("POST", body)
										 .addHeader("Content-Type", "application/json; charset=utf-8")
										 .addHeader("Authorization", "Bearer " + dtoGetToken.getAccess_token())
										 .build();
			System.out.println("<=== REQUEST ===>" + request.toString());
			try {
				System.out.println("<=== RESPONSE EXECUTE SERVICE ===>");
				response = client_http.newCall(request).execute();
			} catch (IOException e) {
				System.out.println("<=== ERROR EN LA CONEXION DEL SERVICIO DE GENERAR INSTRUCCION DE COBRO DE BANBAJIO ===>");
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
				throw new BanBanjioException(Messages.ERROR_CONEXION_INSTRUCCION_COBRO);
			}
			
			String body_responsse = null;
			try {
				body_responsse = response.body().string();
				body_responsse = body_responsse.replace(" ", "");
				System.out.println("<=== RESPUESTA SERVICE ===>" + body_responsse);
			} catch (IOException e) {
				System.out.println("<=== ERROR AL OBTENER LA RESPUESTA DEL SERVICIO DE GENERAR INSTRUCCION COBRO DE BANBAJIO ===>");
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
				throw new BanBanjioException(Messages.ERROR_RESPUESTA_INSTRUCCION_COBRO);
			}
			
			json_object_body = new JSONObject(body_responsse);
			System.out.println("<=== RESPUESTA SERVICE ===>" + json_object_body.toString());
			responseInstruccionCobro.setCodigoRespuesta(((String) json_object_body.get("codigoRespuesta") == null)
													    ? "" : (String) json_object_body.get("codigoRespuesta"));
			responseInstruccionCobro.setMensaje(((String) json_object_body.get("mensaje") == null)
			                                    ? "" : (String) json_object_body.get("mensaje"));
			
			if (responseInstruccionCobro.getMensaje().equals("ERROR")) {
				responseInstruccionCobro.setResultadoError(((String) json_object_body.get("resultadoError") == null) 
				                                            ? "" : (String) json_object_body.get("resultadoError"));
				responseInstruccionCobro.setResultado(null);
				return responseInstruccionCobro;
			}

			JSONObject json_resultado = json_object_body.getJSONObject("resultado");
			resultInstructionCollection.setRespuestaCobro((String) json_resultado.get("respuestaCobro"));
			resultInstructionCollection.setFolioIke((String) json_resultado.get("folioIke"));
			JSONObject json_datos_cliente = json_resultado.getJSONObject("datosCliente");
			costumerData.setDomicilioCompleto(json_datos_cliente.getString("domicilioCompleto"));
			costumerData.setNoCliente(json_datos_cliente.getInt("noCliente"));
			resultInstructionCollection.setDatosClientes(costumerData);
			responseInstruccionCobro.setResultado(resultInstructionCollection);
			System.out.println("<=== OBJECT DTO ===>" + responseInstruccionCobro);
			return responseInstruccionCobro;
		}
		return responseInstruccionCobro;
	}	
}
