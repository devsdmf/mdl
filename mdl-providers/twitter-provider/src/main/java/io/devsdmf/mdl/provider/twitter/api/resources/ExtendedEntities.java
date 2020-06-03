package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.URISyntaxException;
import java.util.*;

public class ExtendedEntities implements Resource{

    private List<Media> media;

    public ExtendedEntities() {}

    public ExtendedEntities(List<Media> media) {
        this.media = media;
    }

    public ExtendedEntities(JsonNode json) throws URISyntaxException {
        if (json.has("media") && json.get("media").isArray()) {
            media = new ArrayList<>();
            for (JsonNode m: json.get("media")) {
                MediaType type = MediaType.fromValue(m.get("type").asText());
                if (type == MediaType.PHOTO) {
                    media.add(new Photo(m));
                } else if (type == MediaType.VIDEO) {
                    media.add(new Video(m));
                } else if (type == MediaType.GIF) {
                    media.add(new AnimatedGif(m));
                }
            }
        }
    }

    public List<Media> getMedia() {
        return media;
    }
}
