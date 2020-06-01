package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.extractor.Extractor;
import io.devsdmf.mdl.provider.twitter.api.ApiClient;
import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;
import io.devsdmf.mdl.provider.twitter.api.resources.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterExtractor implements Extractor {

    private Configuration config;

    private ApiClient client;

    private BearerTokenCredentials credentials;

    private Logger logger;

    public TwitterExtractor(Configuration config, ApiClient client, BearerTokenCredentials credentials) {
        this.config = config;
        this.client = client;
        this.credentials = credentials;
        this.logger = LoggerFactory.getLogger(TwitterExtractor.class);
    }

    public URI extractImageFrom(URI src) throws TwitterException {
        String tweetId = getTweetIdFromUri(src);
        Optional<Tweet> tweet = client.getTweet(tweetId,credentials);

        if (tweet.isPresent()) {
            List<Media> media = tweet.get().getExtendedEntities().getMedia();
            for (Media m: media) {
                if (m.getClass().equals(Photo.class)) {
                    return m.getMediaUrlSecure();
                }
            }

            throw new TwitterException("Could not find any valid photo in the specified tweet");
        } else {
            throw new TwitterException("Tweet not found");
        }
    }

    public URI extractVideoFrom(URI src) throws TwitterException {
        String tweetId = getTweetIdFromUri(src);
        Optional<Tweet> tweet = client.getTweet(tweetId,credentials);

        if (tweet.isPresent()) {
            Resolver videoResolver = new VideoResolver();
            return videoResolver.resolve(tweet.get());
        } else {
            throw new TwitterException("Tweet not found");
        }
    }

    private String getTweetIdFromUri(URI uri) throws TwitterException {
        // TODO: Improve URL parsing and validation
        String path = uri.getPath();

        Pattern p = Pattern.compile("\\/(\\d+)");
        Matcher m = p.matcher(path);

        if (m.find()) {
            return m.group(1).trim();
        }

        throw new TwitterException("Could not extract tweet ID from the specified URL!");
    }
}
