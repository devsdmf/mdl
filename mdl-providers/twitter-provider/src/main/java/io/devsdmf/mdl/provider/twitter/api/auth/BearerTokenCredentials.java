package io.devsdmf.mdl.provider.twitter.api.auth;

import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpMessage;

public class BearerTokenCredentials implements Credentials {

    private final String tokenType = "Bearer";

    private String accessToken;

    public BearerTokenCredentials() {}

    public BearerTokenCredentials(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    private String getHeaderValue() {
        return tokenType + " " + accessToken;
    }

    public HttpMessage apply(HttpMessage request) {
        request.setHeader(HttpHeaders.AUTHORIZATION,getHeaderValue());
        return request;
    }
}