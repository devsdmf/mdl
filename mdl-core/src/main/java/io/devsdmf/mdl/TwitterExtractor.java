package io.devsdmf.mdl;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterExtractor {

    private HttpClient httpClient;

    private String authorizationToken;

    public TwitterExtractor(String authorizationToken) {
        this.httpClient = HttpClients.createDefault();
        this.authorizationToken = authorizationToken;
    }

    public void extract(URL url) throws Exception {
        String tweetId = getTweetIdFromUrl(url);
        System.out.println("TWEET ID >>>>>>> " + tweetId);
    }

    private static String getTweetIdFromUrl(URL url) throws Exception {
        String path = url.getPath();
        System.out.println("PATH >>>>>>>>> " + path);

        Pattern p = Pattern.compile("\\/(\\d+)");
        Matcher m = p.matcher(path);

        if (m.find()) {
            return m.group(1).trim();
        }

        throw new Exception("Could not extract tweet ID from the specified URL!");
    }
}
