package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.provider.UrlMatcher;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterUrlMatcher implements UrlMatcher {

    public static final String PATTERN =
            "^([http(s):\\/\\/(www\\.)twitter.com]{2,256})\\/(\\b[-a-zA-Z0-9@:%_\\+.~#?&=]*)\\/status(?:es)*\\/(\\d+).+$";

    @Override
    public Boolean match(URI url) {
        Pattern p = Pattern.compile(PATTERN,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(url.toString());

        return m.matches();
    }
}
