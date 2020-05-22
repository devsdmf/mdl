package io.devsdmf.mdl.provider.twitter.exception;

public class TwitterException extends Exception {

    public TwitterException(String message) {
        super(message);
    }

    public TwitterException(String message, Throwable cause) {
        super(message,cause);
    }

    public TwitterException(Throwable cause) {
        super(cause);
    }
}