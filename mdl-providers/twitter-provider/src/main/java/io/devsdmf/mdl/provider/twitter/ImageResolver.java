package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.provider.twitter.api.resources.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class ImageResolver implements Resolver {

    @Override
    public URI resolve(Tweet tweet) throws TwitterException {
        Optional<Media> image = filterImage(tweet.getExtendedEntities().getMedia());

        if (image.isPresent()) {
            return image.get().getMediaUrlSecure();
        } else {
            throw new TwitterException("Could not resolve any media of type photo");
        }
    }

    private Optional<Media> filterImage(List<Media> mediaList) {
        return mediaList
                .stream()
                .filter(m -> m.getClass().equals(Photo.class))
                .findAny();
    }
}