package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.URI;
import java.net.URISyntaxException;

public class Media implements Resource {
    private Long id;

    private URI url;

    private URI mediaUrl;

    private URI mediaUrlSecure;

    private MediaType type;

    public Media() {
        this.type = MediaType.UNKNOWN;
    }

    protected Media(MediaType type) {
        this.type = type;
    }

    public Media(JsonNode json) throws URISyntaxException, ResourceException {
        if (json.has("id")) {
            this.id = json.get("id").asLong();
        }

        if (json.has("url")) {
            this.url = new URI(json.get("url").asText());
        }

        if (json.has("medial_url")) {
            this.mediaUrl = new URI(json.get("media_url").asText());
        }

        if (json.has("media_url_https")) {
            this.mediaUrlSecure = new URI(json.get("media_url_https").asText());
        }

        if (json.has("type")) {
            this.type = MediaType.fromValue(json.get("type").asText());
        } else {
            throw new ResourceException("You must specify a valid type");
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public URI getUrl() {
        return url;
    }

    public void setMediaUrl(URI url) {
        this.mediaUrl = url;
    }

    public URI getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrlSecure(URI url) {
        this.mediaUrlSecure = url;
    }

    public URI getMediaUrlSecure() {
        return mediaUrlSecure;
    }

    public MediaType getType() {
        return type;
    }
}


