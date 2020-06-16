package io.devsdmf.mdl.cli;

import io.devsdmf.mdl.cli.configuration.ConfigurationException;
import io.devsdmf.mdl.cli.configuration.GeneralConfiguration;
import io.devsdmf.mdl.cli.configuration.Loader;
import io.devsdmf.mdl.cli.provider.ProviderException;
import io.devsdmf.mdl.cli.provider.ProviderManager;
import io.devsdmf.mdl.downloader.Downloader;
import io.devsdmf.mdl.downloader.SimpleHttpDownloader;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Command
public class Application
{

    private static final String DEFAULT_CONFIGURATION_FILE = ".mdlconfig.yaml";

    public static void main(String[] args) throws ConfigurationException, ProviderException {
        String homeDirectory = System.getProperty("user.home");

        Path configurationFile = Paths.get(homeDirectory + File.separator + DEFAULT_CONFIGURATION_FILE);

        Map<String,Object> baseConfig = Loader.load(configurationFile);
        GeneralConfiguration generalConfig = GeneralConfiguration.factory(baseConfig);

        ProviderManager providerManager = ProviderManager.fromConfiguration(baseConfig);

        Downloader downloader = new SimpleHttpDownloader();

        CommandLine cli = new CommandLine(new Application())
                .addSubcommand(new DownloadCommand(generalConfig,providerManager,downloader));

        System.exit(cli.execute(args));
    }
}
