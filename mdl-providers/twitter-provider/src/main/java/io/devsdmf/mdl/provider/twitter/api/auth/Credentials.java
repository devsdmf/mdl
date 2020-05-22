package io.devsdmf.mdl.provider.twitter.api.auth;

import org.apache.hc.core5.http.HttpMessage;

public interface Credentials {

    public HttpMessage apply(HttpMessage request) throws CredentialsException;
}