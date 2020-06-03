package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.provider.twitter.api.resources.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ImageResolverTests {

    @Test
    public void testResolveImage() throws URISyntaxException, TwitterException {
        // given
        Photo photo = new Photo();
        photo.setId((long) 1);
        photo.setMediaUrlSecure(new URI("https://img.twitter.com/image_full.jpg"));

        List<Media> mediaList = new ArrayList<>();
        mediaList.add(photo);

        Tweet tweet = new Tweet();
        tweet.setId((long)1234);
        tweet.setText("Test tweet");
        tweet.setUser(new User());
        tweet.setExtendedEntities(new ExtendedEntities(mediaList));

        ImageResolver resolver = new ImageResolver();

        // when
        URI result = resolver.resolve(tweet);

        // then
        Assertions.assertEquals("https://img.twitter.com/image_full.jpg",result.toString());
    }

    @Test
    public void testResolveTweetWithoutImage() {
        // given
        Tweet tweet = new Tweet();
        tweet.setId((long) 1234);
        tweet.setText("Test tweet");
        tweet.setUser(new User());
        tweet.setExtendedEntities(new ExtendedEntities());

        ImageResolver resolver = new ImageResolver();

        // when
        Exception ex = Assertions.assertThrows(TwitterException.class, () -> resolver.resolve(tweet));

        // then
        Assertions.assertEquals("Could not resolve any media of type photo", ex.getMessage());
    }


}