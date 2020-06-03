package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;

public class User implements Resource {

    private Long id;

    private String name;

    private String handler;

    public User() {}

    public User(JsonNode json) {
        if (json.has("id")) {
            this.id = json.get("id").asLong();
        }

        if (json.has("name")) {
            this.name = json.get("name").asText();
        }

        if (json.has("screen_name")) {
            this.name = json.get("screen_name").asText();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHandler() {
        return handler;
    }
}