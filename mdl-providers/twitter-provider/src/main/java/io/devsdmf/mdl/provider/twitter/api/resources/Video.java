package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Video extends Media {
    
    private List<Variant> variants;

    public Video() {
        super(MediaType.VIDEO);
        this.variants = new ArrayList<>();
    }

    public Video(List<Variant> variants) {
        this.variants = variants;
    }

    public Video(JsonNode json) throws URISyntaxException, ResourceException {
        super(json);

        if (json.has("video_info")) {
            JsonNode vInfo = json.get("video_info");
            if (vInfo.has("variants") && vInfo.get("variants").isArray()) {
                variants = new ArrayList<>();
                for (JsonNode n: vInfo.get("variants")) {
                    variants.add(new Variant(n));
                }
            }
        }
    }

    public List<Variant> getVariants() {
        return variants;
    }
}
