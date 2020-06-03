package io.devsdmf.mdl.provider.twitter.api;

import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;
import io.devsdmf.mdl.provider.twitter.api.resources.Tweet;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Resource;
import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ApiClientTests {

    @Test
    public void testGetTweetSuccess() throws IOException {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpGet.class),any(HttpClientResponseHandler.class)))
                .thenReturn(Resource.getResourceFileAsString("tweet_with_video.json"));

        // given
        String tweetId = "01234567890";
        BearerTokenCredentials credentials = new BearerTokenCredentials();
        ApiClient client = new ApiClient(httpClient);

        // when
        Optional<Tweet> t = client.getTweet(tweetId,credentials);

        // then
        Assertions.assertTrue(t.isPresent());
    }

    @Test
    public void testGetTweetFail() throws IOException {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpGet.class),any(HttpClientResponseHandler.class)))
                .thenReturn(null);

        // given
        String tweetId = "01234567890";
        BearerTokenCredentials credentials = new BearerTokenCredentials();
        ApiClient client = new ApiClient(httpClient);

        // when
        Optional<Tweet> t = client.getTweet(tweetId,credentials);

        // then
        Assertions.assertFalse(t.isPresent());
    }

    @Test
    public void testGetAccessTokenSuccess() throws IOException {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpPost.class),any(HttpClientResponseHandler.class)))
                .thenReturn(Resource.getResourceFileAsString("access_token.json"));

        // given
        String consumerKey = "TestConsumerKey";
        String consumerSecret = "RandomConsumerSecret";
        ApiClient client = new ApiClient(httpClient);

        // when
        Optional<BearerTokenCredentials> credentials = client.getAccessToken(consumerKey,consumerSecret);

        // then
        Assertions.assertTrue(credentials.isPresent());
    }

    @Test
    public void testGetAccessTokenFailDueToInvalidTokenType() throws IOException {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpPost.class),any(HttpClientResponseHandler.class)))
                .thenReturn(Resource.getResourceFileAsString("access_token_invalid.json"));

        // given
        String consumerKey = "TestConsumerKey";
        String consumerSecret = "RandomConsumerSecret";
        ApiClient client = new ApiClient(httpClient);

        // when
        Optional<BearerTokenCredentials> credentials = client.getAccessToken(consumerKey,consumerSecret);

        // then
        Assertions.assertFalse(credentials.isPresent());
    }

    @Test
    public void testGetAccessTokenFailDueToMissingToken() throws IOException {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpPost.class),any(HttpClientResponseHandler.class)))
                .thenReturn(Resource.getResourceFileAsString("access_token_missing_property.json"));

        // given
        String consumerKey = "TestConsumerKey";
        String consumerSecret = "RandomConsumerSecret";
        ApiClient client = new ApiClient(httpClient);

        // when
        Optional<BearerTokenCredentials> credentials = client.getAccessToken(consumerKey,consumerSecret);

        // then
        Assertions.assertFalse(credentials.isPresent());
    }

    @Test
    public void testGetAccessTokenFail() throws IOException {
        // mock
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpPost.class),any(HttpClientResponseHandler.class)))
                .thenReturn(null);

        // given
        String consumerKey = "TestConsumerKey";
        String consumerSecret = "RandomConsumerSecret";
        ApiClient client = new ApiClient(httpClient);

        // when
        Optional<BearerTokenCredentials> credentials = client.getAccessToken(consumerKey,consumerSecret);

        // then
        Assertions.assertFalse(credentials.isPresent());
    }
}