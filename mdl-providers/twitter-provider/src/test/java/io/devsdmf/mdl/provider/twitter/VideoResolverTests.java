package io.devsdmf.mdl.provider.twitter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import io.devsdmf.mdl.provider.twitter.api.resources.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VideoResolverTests {

    @Test
    public void testResolveVariant() throws URISyntaxException, TwitterException {
        // given
        List<Variant> variants = new ArrayList<>();
        variants.add(createVariant(1,ContentType.VIDEO_MP4,"https://vid.twitter.com/low_quality.mp4"));
        variants.add(createVariant(10000,ContentType.VIDEO_MP4,"https://vid.twitter.com/high_quality.mp4"));
        variants.add(createVariant(5000,ContentType.VIDEO_MP4,"https://vide.twitter.com/medium_quality.mp4"));
        variants.add(createVariant(100000,ContentType.UNKNOWN,"https://vid.twitter.com/unknown_format.mkv"));

        Tweet tweet = getTweet(getMedia(variants));
        VideoResolver resolver = new VideoResolver();

        // when
        URI result = resolver.resolve(tweet);

        // then
        Assertions.assertEquals("https://vid.twitter.com/high_quality.mp4", result.toString());
    }

    @Test
    public void testResolveWithoutValidVariant() throws URISyntaxException {
        // given
        List<Variant> variants = new ArrayList<>();
        variants.add(createVariant(1000,ContentType.UNKNOWN,"https://vid.twitter.com/invalid.mkv"));

        Tweet tweet = getTweet(getMedia(variants));
        VideoResolver resolver = new VideoResolver();

        // when
        Exception ex = Assertions.assertThrows(TwitterException.class, () -> resolver.resolve(tweet));

        // then
        Assertions.assertEquals("Could not find any valid video variant", ex.getMessage());
    }

    @Test
    public void testResolveWithoutVideo() {
        // given
        Tweet tweet = getTweet(new ArrayList<>());
        VideoResolver resolver = new VideoResolver();

        // when
        Exception ex = Assertions.assertThrows(TwitterException.class, () -> resolver.resolve(tweet));

        // then
        Assertions.assertEquals("Could not resolve any media of type video", ex.getMessage());
    }

    private static Tweet getTweet(List<Media> media) {
        Tweet tweet = new Tweet();
        tweet.setId((long)1234);
        tweet.setText("Test tweet");
        tweet.setUser(new User());
        tweet.setExtendedEntities(new ExtendedEntities(media));

        return tweet;
    }

    private static List<Media> getMedia(List<Variant> variants) {
        Video video = new Video(variants);
        List<Media> media = new ArrayList<>();
        media.add(video);

        return media;
    }

    private static Variant createVariant(int bitRate, ContentType contentType, String url) throws URISyntaxException {
        Variant v = new Variant();
        v.setBitRate((long) bitRate);
        v.setContentType(contentType);
        v.setUrl(new URI(url));

        return v;
    }
}