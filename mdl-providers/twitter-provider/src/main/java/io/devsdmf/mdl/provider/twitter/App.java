package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.provider.twitter.api.ApiClient;
import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;
import io.devsdmf.mdl.provider.twitter.api.resources.Tweet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.util.Optional;

public class App {

    private static final String CONSUMER_KEY = "EFsNi1XQ4e62GzHiRcqI9P4Hj";
    private static final String CONSUMER_SECRET = "lGH8odmVI424eqaUJCcvVvbjfgmg5bSARQNPTcwOpTNMnCxGi4";
    private static final String ACCESS_TOKEN = "AAAAAAAAAAAAAAAAAAAAAMHuigAAAAAACS0%2BL7OEdzPFCSVUy1veuIL7qkE%3D4uD3WZsk1jopEYDcfDdDI7mRzTGWavVBGwYdgM5zrmzqxcpTa3";
    private static final String TWEET_ID = "1253042706391203845";

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ApiClient client = new ApiClient(httpClient);

        // generate access token
//        Optional<BearerTokenCredentials> credentials = client.getAccessTokenFor(CONSUMER_KEY,CONSUMER_SECRET);

//        if (credentials.isPresent()) {
//            System.out.println("Successfully generated access token!");
//            System.out.println(credentials.get().getAccessToken());
//        } else {
//            System.out.println("Failed to generate access token");
//        }

        // fetch tweet
        BearerTokenCredentials credentials = new BearerTokenCredentials(ACCESS_TOKEN);
        Optional<Tweet> tweet = client.getTweet(TWEET_ID,credentials);

        if (tweet.isPresent()) {
            System.out.println("Successfully fetched tweet!");
            System.out.println(tweet.get().getId());
        } else {
            System.out.println("Failed to fetch tweet");
        }
    }
}