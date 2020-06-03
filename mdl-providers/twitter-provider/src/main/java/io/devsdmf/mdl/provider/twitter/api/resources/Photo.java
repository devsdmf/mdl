package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;
import java.net.URISyntaxException;

public class Photo extends Media {

    public Photo() {
        super(MediaType.PHOTO);
    }

    public Photo(JsonNode json) throws URISyntaxException, ResourceException {
        super(json);
    }
}