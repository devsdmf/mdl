package io.devsdmf.mdl.twitter;

//import com.sun.org.apache.xml.internal.security.utils.Base64;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.classic.methods.HttpPost;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.core5.http.*;
//import org.apache.hc.core5.http.io.HttpClientResponseHandler;
//import org.apache.hc.core5.http.io.entity.EntityUtils;
//import org.apache.hc.core5.http.io.entity.StringEntity;
//import org.apache.hc.core5.net.URIBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.*;
//import java.net.URI;
//import java.nio.charset.StandardCharsets;
//
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;

public class TwitterExtractor {

//    private static final String BASE_URL = "https://api.twitter.com/";
//
//    private static final String ACCESS_TOKEN_ENDPOINT = "oauth2/token";
//
//    private String accessToken;
//
//    private Logger logger;
//
//    public TwitterExtractor(String accessToken) {
//        this.accessToken = accessToken;
//        this.logger = LoggerFactory.getLogger(TwitterExtractor.class);
//    }
//
//    public void extract(URL url) throws Exception {
//        String tweetId = getTweetIdFromUrl(url);
//        System.out.println("TWEET ID >>>>>>> " + tweetId);
//
//        try {
//            URI uri = new URIBuilder()
//                    .setScheme("https")
//                    .setHost("api.twitter.com")
//                    .setPath("1.1/statuses/show/" + tweetId + ".json")
//                    .build();
//
//            try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
//                final HttpGet req = new HttpGet(uri);
//
//                req.setHeader(HttpHeaders.AUTHORIZATION,"Bearer " + accessToken);
//                req.setHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON);
//
//                final HttpClientResponseHandler<String> resHandler = new HttpClientResponseHandler<String>() {
//                    @Override
//                    public String handleResponse(ClassicHttpResponse res) throws HttpException, IOException {
//                        final int status = res.getCode();
//                        if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_REDIRECTION) {
//                            final HttpEntity entity = res.getEntity();
//                            return entity != null ? EntityUtils.toString(entity) : null;
//                        } else {
//                            logger.warn("Unexpected response status code",res);
//                            throw new HttpException("Unexpected response status: " + status);
//                        }
//                    }
//                };
//
//                final String result = httpClient.execute(req,resHandler);
//                System.out.println(result);
//            }
//        } catch (Exception e) {
//            this.logger.error("An error occurred at try to fetch the tweet information, " +
//                    "an exception was caught",e);
//        }
//    }
//
//    public static String getAccessToken(String apiKey, String apiSecret) {
//        try {
//            String encodedApiKey = URLEncoder.encode(apiKey, StandardCharsets.UTF_8.toString());
//            String encodedSecretKey = URLEncoder.encode(apiSecret, StandardCharsets.UTF_8.toString());
//            String credentials = Base64.encode((encodedApiKey + ":" + encodedSecretKey).getBytes());
//
//            System.out.println(credentials);
//
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            URI uri = new URIBuilder()
//                    .setScheme("https")
//                    .setHost("api.twitter.com")
//                    .setPath("oauth2/token")
//                    .build();
//
//            HttpPost req = new HttpPost(uri);
//            req.setHeader(HttpHeaders.AUTHORIZATION,"Basic " + credentials);
////            req.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
//            req.setEntity(new StringEntity("grant_type=client_credentials",
//                    ContentType.create("application/x-www-form-urlencoded","UTF-8")));
//            CloseableHttpResponse res = httpClient.execute(req);
//
//            try {
//                HttpEntity entity = res.getEntity();
//                if (entity != null) {
//                    if (res.getCode() == HttpStatus.SC_OK) {
//
//                        InputStream instream = entity.getContent();
//                        String body = new BufferedReader(new InputStreamReader(instream,StandardCharsets.UTF_8))
//                                .lines()
//                                .collect(Collectors.joining("\n"));
//
//                        System.out.println(body); // {"token_type":"bearer","access_token":"AAAAAAAAAAAAAAAAAAAAAMHuigAAAAAACS0%2BL7OEdzPFCSVUy1veuIL7qkE%3D4uD3WZsk1jopEYDcfDdDI7mRzTGWavVBGwYdgM5zrmzqxcpTa3"}
//                    } else {
//                        System.out.println("An error occurred at try to execute HTTP request!");
//                        System.out.println("HTTP Status Code: " + res.getCode());
//                        System.out.println("HTTP response body: " + res.getEntity().toString());
//                    }
//                } else {
//                    System.out.println("An error occurred at try to execute request, HTTP response code was: " + res.getCode());
//                }
//            } finally {
//                res.close();
//            }
//
//
//        } catch (Exception e) {
//            System.out.println("An exception with message was caught: " + e.getMessage());
//        }
//
//        return "";
//    }
//
//    private static String getTweetIdFromUrl(URL url) throws Exception {
//        String path = url.getPath();
//        System.out.println("PATH >>>>>>>>> " + path);
//
//        Pattern p = Pattern.compile("\\/(\\d+)");
//        Matcher m = p.matcher(path);
//
//        if (m.find()) {
//            return m.group(1).trim();
//        }
//
//        throw new Exception("Could not extract tweet ID from the specified URL!");
//    }
}
