package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.provider.twitter.api.resources.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class VideoResolver implements Resolver {

    private final static String DEFAULT_CONTENT_TYPE = "video/mp4";

    @Override
    public URI resolve(Tweet tweet) throws TwitterException {
        Optional<Media> video = filterVideo(tweet.getExtendedEntities().getMedia());

        if (video.isPresent()) {
            List<Variant> variants = ((Video) video.get()).getVariants();

            Comparator<Variant> sortBy = Comparator.comparing(Variant::getBitRate).reversed();

            Optional<Variant> variant = variants
                    .stream()
                    .filter(v -> v.getContentType().equals(ContentType.VIDEO_MP4))
                    .min(sortBy);

            if (variant.isPresent()) {
                return variant.get().getUrl();
            } else {
                throw new TwitterException("Could not find any valid video variant");
            }
        } else {
            throw new TwitterException("Could not resolve any media of type video");
        }
    }

    private Optional<Media> filterVideo(List<Media> mediaList) {
        return mediaList
                .stream()
                .filter(m -> m.getClass().equals(Video.class))
                .findAny();

    }
}