package io.devsdmf.mdl.media;

public enum Type {
    PHOTO("photo"),
    VIDEO("video");

    public final String value;

    private Type(String value) {
        this.value = value;
    }

    public static Type fromValue(String value) {
        for (Type t: values()) {
            if (t.value.equals(value)) {
                return t;
            }
        }

        return null;
    }
}