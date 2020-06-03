package io.devsdmf.mdl.matcher;

import java.net.URI;

public interface UrlMatcher {

    public Boolean match(URI url);
}