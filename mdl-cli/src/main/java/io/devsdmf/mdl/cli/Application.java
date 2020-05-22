package io.devsdmf.mdl.cli;

import io.devsdmf.mdl.downloader.Downloader;
import io.devsdmf.mdl.downloader.SimpleHttpDownloader;
import io.devsdmf.mdl.extractor.Extractor;
import io.devsdmf.mdl.provider.twitter.TwitterExtractor;
import io.devsdmf.mdl.provider.twitter.api.ApiClient;
import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Hello world!
 *
 */
public class Application
{

    private static final String TWITTER_ACCESS_TOKEN = "AAAAAAAAAAAAAAAAAAAAAMHuigAAAAAACS0%2BL7OEdzPFCSVUy1veuIL7qkE%3D4uD3WZsk1jopEYDcfDdDI7mRzTGWavVBGwYdgM5zrmzqxcpTa3";

    public static void main(String[] args) {
        try {
            URI tweetUrl = new URI(args[0]);
            String destinationFile = args[1];

            ApiClient twitterClient = new ApiClient(HttpClients.createDefault());
            BearerTokenCredentials twitterCredentials = new BearerTokenCredentials(TWITTER_ACCESS_TOKEN);
            Extractor extractor = new TwitterExtractor(twitterClient,twitterCredentials);

            URI mediaUrl = extractor.extractVideoFrom(tweetUrl);

            if (mediaUrl != null) {
                Downloader downloader = new SimpleHttpDownloader();
                File result = downloader.download(mediaUrl.toURL(),destinationFile);

                if (result != null) {
                    System.out.println("Successfully downloaded file!");
                } else {
                    System.out.println("Unknown error!");
                }
            } else {
                throw new Exception("Could not find any media in the specified URL");
            }

        } catch (MalformedURLException | URISyntaxException e) {
            System.out.println("An error occurred at try to parse the specified URL");
        } catch (Exception e) {
            System.out.println("An error occurred, an exception with message was caught: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
