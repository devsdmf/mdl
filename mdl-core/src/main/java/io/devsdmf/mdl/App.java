package io.devsdmf.mdl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class App
{

    private static final String TWITTER_API_KEY = "tM464K0HLDHkClzF067xvRFnw";
    private static final String TWITTER_API_SECRET = "Pm3mV8D3Ha7ZkJimQTa6hUHJjJZMllgCukwEnOpw5gAiqjWbf6";
    private static final String TWITTER_ACCESS_TOKEN = "AAAAAAAAAAAAAAAAAAAAAMHuigAAAAAACS0%2BL7OEdzPFCSVUy1veuIL7qkE%3D4uD3WZsk1jopEYDcfDdDI7mRzTGWavVBGwYdgM5zrmzqxcpTa3";

    public static void main(String[] args)
    {
        try {
//            String rawUrl = args[0];

            String rawUrl = "https://twitter.com/devsdmf/status/1253042706391203845?s=20";
            URL url = new URL(rawUrl);
            System.out.println("URL => " + rawUrl);

            TwitterExtractor te = new TwitterExtractor(App.TWITTER_ACCESS_TOKEN);
            te.extract(url);

            // get access token
//            String accessToken = TwitterExtractor.getAccessToken(App.TWITTER_API_KEY,App.TWITTER_API_SECRET);
//            System.out.println(accessToken);



//        } catch (MalformedURLException e) {
//            System.out.println(">>>>>> ERROR!");
//            System.out.println(e.getMessage());
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger("main");
            logger.debug(">>>>>>> ERROR",e);
        }

    }
}
