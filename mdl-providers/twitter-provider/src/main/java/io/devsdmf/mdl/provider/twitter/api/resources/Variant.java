package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.net.URISyntaxException;

public class Variant {

    public static final String CONTENT_TYPE_MP4 = "video/mp4";

    private Long bitRate;

    private ContentType contentType;

    private URI url;

    public Variant() {}

    public Variant(JsonNode json) throws URISyntaxException {
        if (json.has("bitrate")) {
            this.bitRate = json.get("bitrate").asLong();
        }

        if (json.has("content_type")) {
            this.contentType = ContentType.fromValue(json.get("content_type").asText());
        }

        if (json.has("url")) {
            this.url = new URI(json.get("url").asText());
        }
    }

    public void setBitRate(Long bitRate) {
        this.bitRate = bitRate;
    }

    public Long getBitRate() {
        return bitRate;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public URI getUrl() {
        return url;
    }
}
