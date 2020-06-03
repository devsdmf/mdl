package io.devsdmf.mdl.provider.twitter.api.resources;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tweet implements Resource {

    private Long id;

    private String text;

    private User user;

    private ExtendedEntities extendedEntities;

    private Date createdAt;

    public Tweet() {
        this.createdAt = new Date();
    }

    public Tweet(JsonNode json) throws URISyntaxException, ParseException {
        if (json.has("id")) {
            this.id = json.get("id").asLong();
        }

        if (json.has("text")) {
            this.text = json.get("text").asText();
        }

        if (json.has("user")) {
            this.user = new User(json.get("user"));
        }

        if (json.has("extended_entities")) {
            this.extendedEntities = new ExtendedEntities(json.get("extended_entities"));
        }

        if (json.has("created_at")) {
            SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
            this.createdAt = df.parse(json.get("created_at").asText());
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setExtendedEntities(ExtendedEntities entities) {
        this.extendedEntities = entities;
    }

    public ExtendedEntities getExtendedEntities() {
        return extendedEntities;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}