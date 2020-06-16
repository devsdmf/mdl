package io.devsdmf.mdl.provider;

import java.net.URI;

public interface UrlMatcher {

    public Boolean match(URI url);
}