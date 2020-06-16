package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.provider.Extractor;
import io.devsdmf.mdl.provider.twitter.api.ApiClient;
import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Resource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class TwitterExtractorTests {

    @Test
    public void testExtractMediaFromInvalidUrl() throws URISyntaxException {
        // mock
        HttpClient httpClient = mock(HttpClient.class);

        // given
        URI tweetUrl = new URI("https://twitter.com/devsdmf");
        Extractor extractor = new TwitterExtractor(new ApiClient(httpClient), new BearerTokenCredentials());

        // when
        Exception ex = Assertions.assertThrows(TwitterException.class, () -> extractor.extractVideoFrom(tweetUrl));

        // then
        Assertions.assertEquals("Could not extract tweet ID from the specified URL!", ex.getMessage());
    }

    @Test
    public void testExtractVideoSuccess() throws Exception {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(),any(HttpClientResponseHandler.class)))
            .thenReturn(Resource.getResourceFileAsString("tweet_with_video.json"));

        // given
        URI tweetUrl = new URI("https://twitter.com/devsdmf/status/1261658226283732992?s=20");
        Extractor extractor = new TwitterExtractor(new ApiClient(httpClient), new BearerTokenCredentials());

        // when
        URI videoUrl = extractor.extractVideoFrom(tweetUrl);

        // then
        Assertions.assertNotNull(videoUrl);
    }

    @Test
    public void testExtractVideoFailTweetWithoutVideo() throws IOException, URISyntaxException {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(),any(HttpClientResponseHandler.class)))
            .thenReturn(Resource.getResourceFileAsString("tweet_without_video.json"));

        // given
        URI tweetUrl = new URI("https://twitter.com/devsdmf/status/1261658226283732992?s=20");
        Extractor extractor = new TwitterExtractor(new ApiClient(httpClient), new BearerTokenCredentials());

        // when
        Exception ex = Assertions.assertThrows(TwitterException.class, () -> extractor.extractVideoFrom(tweetUrl));

        // then
        Assertions.assertEquals("Could not resolve any media of type video", ex.getMessage());
    }

    @Test
    public void testExtractVideoFailTweetWithoutValidVariant() throws IOException, URISyntaxException {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(),any(HttpClientResponseHandler.class)))
                .thenReturn(Resource.getResourceFileAsString("tweet_with_invalid_video_variant.json"));

        // given
        URI tweetUrl = new URI("https://twitter.com/devsdmf/status/1261658226283732992?s=20");
        Extractor extractor = new TwitterExtractor(new ApiClient(httpClient), new BearerTokenCredentials());

        // when
        Exception ex = Assertions.assertThrows(TwitterException.class, () -> extractor.extractVideoFrom(tweetUrl));

        // then
        Assertions.assertEquals("Could not find any valid video variant", ex.getMessage());
    }

    @Test
    public void testExtractVideoFailTweetNotExists() throws Exception {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(),any(HttpClientResponseHandler.class)))
                .thenReturn(null);

        // given
        URI tweetUrl = new URI("https://twitter.com/devsdmf/status/1261658226283732992?s=20");
        Extractor extractor = new TwitterExtractor(new ApiClient(httpClient), new BearerTokenCredentials());

        // when
        Exception ex = Assertions.assertThrows(TwitterException.class, () -> extractor.extractVideoFrom(tweetUrl));

        // then
        Assertions.assertEquals("Tweet not found",ex.getMessage());
    }
}