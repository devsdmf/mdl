package io.devsdmf.mdl.cli;

import com.fasterxml.jackson.databind.JsonNode;
import io.devsdmf.mdl.cli.configuration.ConfigurationException;
import io.devsdmf.mdl.cli.configuration.GeneralConfiguration;
import io.devsdmf.mdl.cli.configuration.Loader;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Application
{

    private static final String DEFAULT_CONFIGURATION_FILE = ".mdlconfig.yaml";

    public static void main(String[] args) throws ConfigurationException {
        String homeDirectory = System.getProperty("user.home");
        Path configurationFile = Paths.get(homeDirectory + File.separator + DEFAULT_CONFIGURATION_FILE);
        Map<String,Object> baseConfig = Loader.load(configurationFile);
        GeneralConfiguration generalConfig = GeneralConfiguration.factory(baseConfig);

        CommandLine command = new CommandLine(new DownloadCommand());
        System.exit(command.execute(args));

        /*try {
            URI tweetUrl = new URI(args[0]);
            String destinationFile = args[1];

            Configuration config = new Configuration();
            ApiClient twitterClient = new ApiClient(HttpClients.createDefault());
            BearerTokenCredentials twitterCredentials = new BearerTokenCredentials(TWITTER_ACCESS_TOKEN);
            Extractor extractor = new TwitterExtractor(config,twitterClient,twitterCredentials);

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
        }*/
    }
}
