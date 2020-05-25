package io.devsdmf.mdl.provider.twitter.api;

import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;
import io.devsdmf.mdl.provider.twitter.api.resources.Tweet;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
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
    public void sampleTest() {
        Assertions.assertEquals("hello","hello");
    }

    @Test
    public void testGetTweetHappy() throws IOException {
        // given
        String tweetId = "01234567890";
        BearerTokenCredentials credentials = new BearerTokenCredentials();

        // when
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpGet.class),any(HttpClientResponseHandler.class)))
            .thenReturn(getResourceFileAsString("get-tweet-success.json"));

        ApiClient client = new ApiClient(httpClient);
        Optional<Tweet> t = client.getTweet(tweetId,credentials);

        // then
        Assertions.assertTrue(t.isPresent());
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