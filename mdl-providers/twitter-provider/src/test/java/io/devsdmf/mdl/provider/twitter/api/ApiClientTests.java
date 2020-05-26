package io.devsdmf.mdl.provider.twitter.api;

import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;
import io.devsdmf.mdl.provider.twitter.api.resources.Tweet;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class ApiClientTests {

    @Test
    public void testGetTweetSuccess() throws IOException {
        // given
        String tweetId = "01234567890";
        BearerTokenCredentials credentials = new BearerTokenCredentials();

        // when
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpGet.class),any(HttpClientResponseHandler.class)))
            .thenReturn(getResourceFileAsString("tweet.json"));

        ApiClient client = new ApiClient(httpClient);
        Optional<Tweet> t = client.getTweet(tweetId,credentials);

        // then
        Assertions.assertTrue(t.isPresent());
    }

    @Test
    public void testGetTweetFail() throws IOException {
        // given
        String tweetId = "01234567890";
        BearerTokenCredentials credentials = new BearerTokenCredentials();

        // when
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpGet.class),any(HttpClientResponseHandler.class)))
            .thenReturn(null);

        ApiClient client = new ApiClient(httpClient);
        Optional<Tweet> t = client.getTweet(tweetId,credentials);

        // then
        Assertions.assertFalse(t.isPresent());
    }

    @Test
    public void testGetAccessTokenSuccess() throws IOException {
        // given
        String consumerKey = "TestConsumerKey";
        String consumerSecret = "RandomConsumerSecret";

        // when
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpPost.class),any(HttpClientResponseHandler.class)))
            .thenReturn(getResourceFileAsString("access_token.json"));

        ApiClient client = new ApiClient(httpClient);
        Optional<BearerTokenCredentials> credentials = client.getAccessToken(consumerKey,consumerSecret);

        // then
        Assertions.assertTrue(credentials.isPresent());
    }

    @Test
    public void testGetAccessTokenFailDueToInvalidTokenType() throws IOException {
        // given
        String consumerKey = "TestConsumerKey";
        String consumerSecret = "RandomConsumerSecret";

        // when
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpPost.class),any(HttpClientResponseHandler.class)))
                .thenReturn(getResourceFileAsString("access_token_invalid.json"));

        ApiClient client = new ApiClient(httpClient);
        Optional<BearerTokenCredentials> credentials = client.getAccessToken(consumerKey,consumerSecret);

        // then
        Assertions.assertFalse(credentials.isPresent());
    }

    @Test
    public void testGetAccessTokenFailDueToMissingToken() throws IOException {
        // given
        String consumerKey = "TestConsumerKey";
        String consumerSecret = "RandomConsumerSecret";

        // when
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpPost.class),any(HttpClientResponseHandler.class)))
                .thenReturn(getResourceFileAsString("access_token_missing_property.json"));

        ApiClient client = new ApiClient(httpClient);
        Optional<BearerTokenCredentials> credentials = client.getAccessToken(consumerKey,consumerSecret);

        // then
        Assertions.assertFalse(credentials.isPresent());
    }

    @Test
    public void testGetAccessTokenFail() throws IOException {
        // given
        String consumerKey = "TestConsumerKey";
        String consumerSecret = "RandomConsumerSecret";

        // when
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpPost.class),any(HttpClientResponseHandler.class)))
                .thenReturn(null);

        ApiClient client = new ApiClient(httpClient);
        Optional<BearerTokenCredentials> credentials = client.getAccessToken(consumerKey,consumerSecret);

        // then
        Assertions.assertFalse(credentials.isPresent());
    }

    private static String getResourceFileAsString(String filename) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream in = classLoader.getResourceAsStream(filename)) {
            if (in == null) return null;
            try (InputStreamReader reader = new InputStreamReader(in)) {
                BufferedReader buffer = new BufferedReader(reader);
                return buffer.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}