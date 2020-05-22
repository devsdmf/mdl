package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.provider.twitter.api.ApiClient;
import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;
import io.devsdmf.mdl.provider.twitter.api.resources.Tweet;
import io.devsdmf.mdl.provider.twitter.exception.TwitterException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.net.URI;
import java.net.URISyntaxException;
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
//        BearerTokenCredentials credentials = new BearerTokenCredentials(ACCESS_TOKEN);
//        Optional<Tweet> tweet = client.getTweet(TWEET_ID,credentials);
//
//        if (tweet.isPresent()) {
//            System.out.println("Successfully fetched tweet!");
//            System.out.println(tweet.get().getId());
//        } else {
//            System.out.println("Failed to fetch tweet");
//        }

        // extract video from tweet
//        BearerTokenCredentials credentials = new BearerTokenCredentials(ACCESS_TOKEN);
//        TwitterExtractor extractor = new TwitterExtractor(client,credentials);

//        try {
//            URI videoUrl = extractor.extractVideoFrom(new URI("https://twitter.com/devsdmf/status/1261658226283732992?s=20"));
//
//            if (videoUrl != null) {
//                System.out.println("VIDEO URL ==> " + videoUrl.toString());
//            } else {
//                System.out.println("Could not find video URL");
//            }
//        } catch (TwitterException e) {
//            System.out.println("An error occurred at try to extract video from tweet, " +
//                    "an exception with message was caught: " + e.getMessage());
//        } catch (URISyntaxException e) {
//            System.out.println("An error occurred at try to parse URI");
//        }

        // extract image from tweet
        BearerTokenCredentials credentials = new BearerTokenCredentials(ACCESS_TOKEN);
        TwitterExtractor extractor = new TwitterExtractor(client,credentials);

        try {
            URI imageUrl = extractor.extractImageFrom(new URI("https://twitter.com/devsdmf/status/1261770150208065538?s=20"));

            if (imageUrl != null) {
                System.out.println("IMAGE URL => " + imageUrl.toString());
            } else {
                System.out.println("Could not find image URL");
            }
        } catch (TwitterException e) {
            System.out.println("An error occurred at try to extract image from tweet, " +
                    "an exception with message was caught: " + e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("An error occurred at try to parse URI");
        }
    }
}