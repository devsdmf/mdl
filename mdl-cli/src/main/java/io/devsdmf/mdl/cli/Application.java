package io.devsdmf.mdl.cli;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;
import picocli.CommandLine;

import java.nio.file.Paths;
import java.util.Arrays;

public class Application
{

    private static final String CONFIGURATION_FILE = ".mdlconfig.yaml";

    public static void main(String[] args) {
        ConfigFilesProvider filesProvider = () -> Arrays.asList(Paths.get(CONFIGURATION_FILE));
        ConfigurationSource source = new FilesConfigurationSource(filesProvider);
        ConfigurationProvider configProvider = new ConfigurationProviderBuilder()
                .withConfigurationSource(source).build();

        Configuration generalConfig = configProvider.bind("general",Configuration.class);
        String downloadPath = generalConfig.downloadPath();
        String accessToken = generalConfig.twitterAccessToken();

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
