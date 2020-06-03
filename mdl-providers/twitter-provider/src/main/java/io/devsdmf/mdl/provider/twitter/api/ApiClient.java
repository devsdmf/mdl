package io.devsdmf.mdl.provider.twitter.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;
import io.devsdmf.mdl.provider.twitter.api.auth.ConsumerKeySecretCredentials;
import io.devsdmf.mdl.provider.twitter.api.auth.CredentialsException;
import io.devsdmf.mdl.provider.twitter.api.resources.ResourceException;
import io.devsdmf.mdl.provider.twitter.api.resources.Tweet;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class ApiClient {

    private final static String SCHEME = "https";
    private final static String HOST = "api.twitter.com";
    private final static String API_VERSION = "1.1";

    private static final String DATE_FORMAT = "E MMM dd H:m:s Z yyyy";

    private final HttpClient httpClient;

    private final ObjectMapper mapper;

    private final Logger logger;

    public ApiClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.logger = LoggerFactory.getLogger(ApiClient.class);
        this.mapper = new ObjectMapper();
        this.mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
    }

    public Optional<Tweet> getTweet(String tweetId, BearerTokenCredentials credentials) {
        try {
            logger.debug(String.format("Attempting to fetch tweet [id=%s]", tweetId));

            URI requestURI = getBaseURI()
                    .setPath(API_VERSION + "/statuses/show.json")
                    .setParameter("id", tweetId)
                    .setParameter("tweet_mode", "extended")
                    .build();

            HttpGet request = new HttpGet(requestURI);
            request = (HttpGet) credentials.apply(request);

            String result = httpClient.execute(request, new ResponseHandler());
            if (result != null) {
                JsonNode json = mapper.readTree(result);
                return Optional.of(new Tweet(json));
            }

            return Optional.empty();
        } catch (ResourceException e) {
            logger.error("An error occurred at trying to parse the requested tweet, " +
                    "an exception was caught", e);
            return Optional.empty();
        } catch (URISyntaxException | IOException e) {
            logger.error("An error occurred at try to request access token, an exception was caught.",e);
            return Optional.empty();
        }
    }

    public Optional<BearerTokenCredentials> getAccessToken(String consumerKey, String consumerSecret) {
        try {
            logger.debug(String.format(
                    "Attempting to request access token for [consumerKey=%s] and [consumerSecret=%s]",
                    consumerKey, consumerSecret
            ));

            URI requestUri = getBaseURI().setPath("oauth2/token").build();
            HttpPost request = new HttpPost(requestUri);

            ConsumerKeySecretCredentials credentials = new ConsumerKeySecretCredentials(consumerKey, consumerSecret);
            request = (HttpPost) credentials.apply(request);

            request.setEntity(new StringEntity("grant_type=client_credentials",
                    ContentType.create("application/x-www-form-urlencoded",
                            StandardCharsets.UTF_8.toString())));

            String result = httpClient.execute(request, new ResponseHandler());
            if (result != null) {
                JsonNode json = mapper.readTree(result);
                if (json.has("token_type") && json.has("access_token")) {
                    String tokenType = json.get("token_type").asText("");

                    if (tokenType.equalsIgnoreCase("bearer")) {
                        logger.info("Successfully generated the requested access token");
                        return Optional.of(new BearerTokenCredentials(json.get("access_token").asText()));
                    } else {
                        logger.error(String.format(
                                "Received an unexpected token type, expected bearer, got '%s'", tokenType
                        ));
                        return Optional.empty();
                    }
                } else {
                    logger.error(String.format("Received unexpected response body: '%s'", result));
                    return Optional.empty();
                }
            }

            return Optional.empty();
        } catch (CredentialsException e) {
            logger.error("An error occurred at try to setup credentials for request, " +
                    "an exception with message was caught: " + e.getMessage(),e);
            return Optional.empty();
        } catch (URISyntaxException | IOException e) {
            logger.error("An error occurred at try to request access token, an exception was caught.",e);
            return Optional.empty();
        }
    }

    private URIBuilder getBaseURI() {
        return new URIBuilder()
                .setScheme(SCHEME)
                .setHost(HOST);
    }
}
