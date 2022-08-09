package com.ike.service.common.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ike.service.common.dto.OkHttpsClient;

import okhttp3.OkHttpClient;

@Component
public class OkHttpsVerifyTLS {

	public OkHttpClient getSocketFactory() throws Exception {
    	System.out.println("<=== IMPLEMENTACION VERIFY TLS ===>");
        System.out.println(OkHttpsClient.AMBIENTE);

    	OkHttpClient.Builder newBuilder = null;
        InputStream cerInputStream = null;
        InputStream p12InputStream = null;
        SSLSocketFactory socketFactory = null;

        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
            };

        try {
            if (OkHttpsClient.AMBIENTE.equals("PROD")){
                cerInputStream = new FileInputStream(new File(OkHttpsClient.PEM_PATH));
            } else {
            	cerInputStream = new FileInputStream(new File(OkHttpsClient.CER_PATH));
            }
            p12InputStream = new FileInputStream(new File(OkHttpsClient.PFX_PATH));
            KeyManager[] keyManagers = getKeyManagers(p12InputStream, OkHttpsClient.PASSWORD_CER);
            TrustManager[] trustManagers = getTrustManagers(cerInputStream);
            SSLContext sslContext = getSslContext(keyManagers, trustManagers);
            socketFactory = sslContext.getSocketFactory();

            newBuilder = new OkHttpClient.Builder();
            newBuilder.connectTimeout(90, TimeUnit.SECONDS);
            newBuilder.writeTimeout(90, TimeUnit.SECONDS);
            newBuilder.readTimeout(90, TimeUnit.SECONDS);
            newBuilder.callTimeout(90, TimeUnit.SECONDS);
            newBuilder.sslSocketFactory(socketFactory, (X509TrustManager) trustAllCerts[0]);
            newBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    System.out.println("Verify Successful: " + s);
                    return true;
                }
            });
            return newBuilder.build();
        } finally {
            if (cerInputStream != null) {
                cerInputStream.close();
            }
            if (p12InputStream != null) {
                p12InputStream.close();
            }
        }
    }

    private static SSLContext getSslContext(KeyManager[] keyManagers, TrustManager[] trustManagers) throws Exception {
        SSLContext sslContext = SSLContext.getInstance(OkHttpsClient.PROTOCOL_TLS);
        sslContext.init(keyManagers, trustManagers, new SecureRandom());
        return sslContext;
    }

    private static KeyManager[] getKeyManagers(InputStream inputStream, String password) throws Exception {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = KeyStore.getInstance(OkHttpsClient.KEY_STORE_TYPE);
        keyStore.load(inputStream, password.toCharArray());
        keyManagerFactory.init(keyStore, password.toCharArray());
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
        return keyManagers;
    }

    private static TrustManager[] getTrustManagers(InputStream inputStream) throws Exception {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = KeyStore.getInstance(OkHttpsClient.KEY_STORE_TYPE);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Certificate ca = certificateFactory.generateCertificate(inputStream);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("server", ca);
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        return trustManagers;
    }

	public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}

	public OkHttpClient getTrustAllCertsClient() throws NoSuchAlgorithmException, KeyManagementException {

        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
        newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
        newBuilder.hostnameVerifier((hostname, session) -> true);
        return newBuilder.build();
    }
}
