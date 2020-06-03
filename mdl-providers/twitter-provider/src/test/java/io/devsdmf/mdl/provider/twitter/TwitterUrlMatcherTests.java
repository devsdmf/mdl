package io.devsdmf.mdl.provider.twitter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterUrlMatcherTests {

    @Test
    public void testMatchValidUrls() {
        // given
        List<URI> urls = (List<URI>) getValidUrls().stream().map(url -> {
            try {
                return new URI(url);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        TwitterUrlMatcher matcher = new TwitterUrlMatcher();

        // when
        List<Boolean> results = urls.stream()
                                    .map(matcher::match)
                                    .collect(Collectors.toList());

        // then
        for (Boolean r: results) {
            Assertions.assertTrue(r);
        }
    }

    @Test
    public void testMatchInvalidUrls() throws URISyntaxException {
        // given
        URI url = new URI("https://www.youtube.com/devsdmf");
        TwitterUrlMatcher matcher = new TwitterUrlMatcher();

        // when
        Boolean result = matcher.match(url);

        // then
        Assertions.assertFalse(result);
    }

    private static List<String> getValidUrls() {
        List<String> urls = new ArrayList<>();
        urls.add("https://twitter.com/devsdmf/status/1235039437580460032?s=20");
        urls.add("http://twitter.com/devsdmf/status/1235039437580460032?s=20");
        urls.add("https://www.twitter.com/devsdmf/status/1235039437580460032?s=20");
        urls.add("http://www.twitter.com/devsdmf/status/1235039437580460032?s=20");
        urls.add("https://twitter.com/devsdmf/status/1235039437580460032");
        urls.add("http://twitter.com/devsdmf/status/1235039437580460032");
        urls.add("https://www.twitter.com/devsdmf/status/1235039437580460032");
        urls.add("http://www.twitter.com/devsdmf/status/1235039437580460032");
        urls.add("twitter.com/devsdmf/status/1235039437580460032?s=20");
        urls.add("twitter.com/devsdmf/status/1235039437580460032?s=20");
        urls.add("www.twitter.com/devsdmf/status/1235039437580460032?s=20");
        urls.add("www.twitter.com/devsdmf/status/1235039437580460032?s=20");
        urls.add("twitter.com/devsdmf/status/1235039437580460032");
        urls.add("twitter.com/devsdmf/status/1235039437580460032");
        urls.add("www.twitter.com/devsdmf/status/1235039437580460032");
        urls.add("www.twitter.com/devsdmf/status/1235039437580460032");

        return urls;
    }
}