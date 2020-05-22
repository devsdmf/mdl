package io.devsdmf.mdl.extractor;

import java.net.URI;

public interface Extractor {

    public URI extractImageFrom(URI src) throws Exception;

    public URI extractVideoFrom(URI src) throws Exception;
}