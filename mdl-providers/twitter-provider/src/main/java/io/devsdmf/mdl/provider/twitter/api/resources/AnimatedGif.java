package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AnimatedGif extends Media {

    private List<Variant> variants;

    public AnimatedGif() {}

    public AnimatedGif(JsonNode json) throws URISyntaxException {
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