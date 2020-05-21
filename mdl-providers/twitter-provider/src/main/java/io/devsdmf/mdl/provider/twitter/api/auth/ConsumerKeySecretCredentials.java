package io.devsdmf.mdl.provider.twitter.api.auth;

import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpMessage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.function.Function;

public class ConsumerKeySecretCredentials implements Credentials {

    private final String tokenType = "Basic";

    private String key;

    private String secret;

    public ConsumerKeySecretCredentials(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    private String getHeaderValue() throws CredentialsException {
        Function<String,String> encode = (val) -> {
            try {
                return URLEncoder.encode(val, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        };

        String key = encode.apply(this.key);
        String secret = encode.apply(this.secret);

        if (key == null || secret == null) {
            throw new CredentialsException("An error occurred at try to encode consumer key and secret.");
        }

        String credentials = Base64.getEncoder().encodeToString((key + ":" + secret).getBytes());

        return tokenType + " " + credentials;
    }

    public HttpMessage apply(HttpMessage request) throws CredentialsException {
        request.setHeader(HttpHeaders.AUTHORIZATION,getHeaderValue());
        return request;
    }
}
