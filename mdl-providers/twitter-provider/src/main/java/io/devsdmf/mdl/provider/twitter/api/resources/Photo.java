package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;
import java.net.URISyntaxException;

public class Photo extends Media {

    public Photo() {}

    public Photo(JsonNode json) throws URISyntaxException {
        super(json);
    }
}