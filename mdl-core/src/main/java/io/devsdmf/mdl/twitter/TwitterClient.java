package io.devsdmf.mdl.twitter;

//import org.apache.hc.client5.http.classic.methods.HttpPost;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.core5.http.*;
//import org.apache.hc.core5.http.io.HttpClientResponseHandler;
//import org.apache.hc.core5.http.io.entity.EntityUtils;
//import org.apache.hc.core5.http.io.entity.StringEntity;
//import org.apache.hc.core5.net.URIBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Optional;

public class TwitterClient {

//    private final static String SCHEME = "https";
//    private final static String HOST = "api.twitter.com";
//    private final static String API_VERSION = "1.1";
//
//    private CloseableHttpClient httpClient;
//
//    private Logger logger;
//
//    public TwitterClient(CloseableHttpClient httpClient) {
//        this.httpClient = httpClient;
//        this.logger = LoggerFactory.getLogger(TwitterClient.class);
//    }
//
//    public Optional<String> getApplicationAccessToken(String consumerKey, String consumerSecret) {
//        try {
//            logger.debug(String.format(
//                    "Attempting to request access token for [consumerKey=%s] and [consumerSecret=%s]",
//                    consumerKey, consumerSecret
//            ));
//
//            String encodedKey = URLEncoder.encode(consumerKey, StandardCharsets.UTF_8.toString());
//            String encodedSecret = URLEncoder.encode(consumerSecret, StandardCharsets.UTF_8.toString());
//
//            String credentials = Arrays.toString(Base64.getEncoder().encode(
//                    (encodedKey + ":" + encodedSecret).getBytes())
//            );
//
//            URI uri = buildURI("oauth2/token").build();
//
//            HttpPost request = new HttpPost(uri);
//            request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentials);
//            request.setEntity(new StringEntity("grant_type=client_credentials",
//                    ContentType.create("application/x-www-form-urlencoded",
//                            StandardCharsets.UTF_8.toString())));
//
//            HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {
//                @Override
//                public String handleResponse(ClassicHttpResponse res) throws HttpException, IOException {
//                    int status = res.getCode();
//
//                    if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_REDIRECTION) {
//                        HttpEntity entity = res.getEntity();
//                        return entity != null ? EntityUtils.toString(entity) : null;
//                    } else {
//                        logger.warn(String.format(
//                                "Unexpected response received with status code %d", status
//                        ), res);
//                        return null;
//                    }
//                }
//            };
//
//            String result = httpClient.execute(request,responseHandler);
//            return Optional.ofNullable(result);
//        } catch (UnsupportedEncodingException | URISyntaxException e) {
//            logger.error("An error occurred at try to request access token, an exception was caught.",e);
//            return Optional.empty();
//        } catch (IOException e) {
//            logger.error("An error occurred at try to request access token, an IOException was caught.", e);
//            return Optional.empty();
//        }
//    }
//
//    private URIBuilder buildURI(String path) {
//        return new URIBuilder()
//                .setScheme(SCHEME)
//                .setHost(HOST)
//                .setPath(API_VERSION + "/" + path);
//    }
}
