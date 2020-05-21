package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.extractor.Extractor;
import io.devsdmf.mdl.provider.twitter.exception.TwitterException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterExtractor implements Extractor {

    public URI extractVideoFrom(URI src) throws TwitterException, URISyntaxException {
        String tweetId = getTweetIdFromUri(src);
        return new URI(tweetId);
    }

    private String getTweetIdFromUri(URI uri) throws TwitterException {
        String path = uri.getPath();

        Pattern p = Pattern.compile("\\/(\\d+)");
        Matcher m = p.matcher(path);

        if (m.find()) {
            return m.group(1).trim();
        }

        throw new TwitterException("Could not extract tweet ID from the specified URL!");
    }
}
