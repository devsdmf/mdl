package io.devsdmf.mdl.provider.twitter.api.resources;

public enum ContentType {
    VIDEO_MP4("video/mp4"),
    UNKNOWN("unknown");

    public final String value;

    private ContentType(String value) {
        this.value = value;
    }

    public static ContentType fromValue(String value) {
        for (ContentType c: values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }

        return UNKNOWN;
    }
}