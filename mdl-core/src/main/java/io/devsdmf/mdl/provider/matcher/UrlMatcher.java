package io.devsdmf.mdl.provider.matcher;

import java.net.URI;

public interface UrlMatcher {

    public Boolean match(URI url);
}