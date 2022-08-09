package com.ike.service.common.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OkHttpsClient {

	public static String AMBIENTE;
    public static String PROTOCOL_TLS;
    public static String KEY_STORE_TYPE;
    public static String CER_PATH;
    public static String PEM_PATH;
    public static String PFX_PATH;
    public static String PASSWORD_CER;
    
	@Value("${api.hub.ambiente}")
	public void setAmbiente(String ambiente) {
		AMBIENTE = ambiente;
	}
	
   @Value("${protocol.tls}")
   public void setProtocolTls(String protocolTls) {
	   PROTOCOL_TLS = protocolTls;
   }
   
   @Value("${key.store.type}")
   public void setKeyStoreType(String keyStoreType) {
	   KEY_STORE_TYPE = keyStoreType;
   }
   
   @Value("${cer.path}")
   public void setCerPath(String cerPath) {
	   CER_PATH = cerPath;
   }
   
   @Value("${pem.path}")
   public void setPemPath(String pemPath) {
	   PEM_PATH = pemPath;
   }
   
   @Value("${pfx.path}")
   public void setPfxPath(String pfxPath) {
	   PFX_PATH = pfxPath;
   }
   
   @Value("${password.cer}")
   public void setPasswordCer(String passwordCer) {
	   PASSWORD_CER = passwordCer;
   }
}
