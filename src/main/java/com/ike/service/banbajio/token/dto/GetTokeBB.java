package com.ike.service.banbajio.token.dto;

public class GetTokeBB {

    private static String access_token;
    private static Integer expires_in;
    private static String token_type;
    private static String scope;
    private static Integer status;

    public static String getAccess_token() {
        return access_token;
    }

    public static void setAccess_token(String access_token) {
        GetTokeBB.access_token = access_token;
    }

    public static Integer getExpires_in() {
        return expires_in;
    }

    public static void setExpires_in(Integer expires_in) {
        GetTokeBB.expires_in = expires_in;
    }

    public static String getToken_type() {
        return token_type;
    }

    public static void setToken_type(String token_type) {
        GetTokeBB.token_type = token_type;
    }

    public static String getScope() {
        return scope;
    }

    public static void setScope(String scope) {
        GetTokeBB.scope = scope;
    }

    public static Integer getStatus() {
        return status;
    }

    public static void setStatus(Integer status) {
        GetTokeBB.status = status;
    }
}
