package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.net.URISyntaxException;

public class Variant {

    private Long bitRate;

    private String contentType;

    private URI url;

    public Variant() {}

    public Variant(JsonNode json) throws URISyntaxException {
        if (json.has("bitrate")) {
            this.bitRate = json.get("bitrate").asLong();
        }

        if (json.has("content_type")) {
            this.contentType = json.get("content_type").asText();
        }

        if (json.has("url")) {
            this.url = new URI(json.get("url").asText());
        }
    }

    public Long getBitRate() {
        return bitRate;
    }

    public String getContentType() {
        return contentType;
    }

    public URI getUrl() {
        return url;
    }
}
