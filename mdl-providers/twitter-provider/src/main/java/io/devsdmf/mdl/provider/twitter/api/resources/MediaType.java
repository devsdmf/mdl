package io.devsdmf.mdl.provider.twitter.api.resources;

public enum MediaType {
    PHOTO("photo"),
    VIDEO("video"),
    GIF("animated_gif"),
    UNKNOWN("unknown");

    public final String value;

    private MediaType(String value) {
        this.value = value;
    }

    public static MediaType fromValue(String value) {
        for (MediaType m: values()) {
            if (m.value.equals(value)) {
                return m;
            }
        }

        return null;
    }
}