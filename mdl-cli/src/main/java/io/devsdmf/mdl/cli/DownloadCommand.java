package io.devsdmf.mdl.cli;

import io.devsdmf.mdl.cli.configuration.GeneralConfiguration;
import io.devsdmf.mdl.cli.converter.TypeConverter;
import io.devsdmf.mdl.cli.provider.ProviderManager;
import io.devsdmf.mdl.downloader.Downloader;
import io.devsdmf.mdl.media.Type;
import io.devsdmf.mdl.provider.Extractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@Command(aliases = {"download", "dl"})
public class DownloadCommand implements Callable<Integer> {

    @Parameters(paramLabel = "Media URL", description = "The URL of the media to be downloaded")
    private String url;

    @Option(
        names = {"-p", "--provider"},
        paramLabel = "provider",
        description = "The source provider",
        required = true
    )
    private String provider;

    @Option(
        names = {"-t", "--type"},
        paramLabel = "type",
        description = "The media type",
        required = true,
        converter = TypeConverter.class
    )
    private Type type;

    @Option(
        names = {"-o", "--out"},
        paramLabel = "/path/to/file.ext",
        description = "The filename (and path) for the output file"
    )
    private String output;

    @Option(
        names = {"-v","--verbose"},
        paramLabel = "Verbose mode",
        description = "Enable verbose logs, defaults false.",
        negatable = true
    )
    private boolean verbose;

    @Option(
        names = {"-h","--help"},
        usageHelp = true,
        description = "Display help message"
    )
    private boolean help;

    private GeneralConfiguration config;

    private ProviderManager pm;

    private Downloader downloader;

    private Logger logger;

    public DownloadCommand(GeneralConfiguration config, ProviderManager pm, Downloader downloader) {
        this.config = config;
        this.pm = pm;
        this.downloader = downloader;

        this.logger = LoggerFactory.getLogger(DownloadCommand.class);
    }

    @Override
    public Integer call() throws Exception {
        URI src = new URI(url);
        Extractor ex = pm.getExtractor(provider);

        URI fileUrl = null;
        if (type == Type.PHOTO) {
            fileUrl = ex.extractImageFrom(src);
        } else if (type == Type.VIDEO) {
            fileUrl = ex.extractVideoFrom(src);
        } else {
            throw new Exception("The specified media type is not valid or is not available");
        }

        String tempFilename = getTemporaryFilename();
        String tempFilePath = config.getTempFolder() + File.separator + tempFilename;

        File downloadedFile = downloader.download(fileUrl.toURL(),tempFilePath);

        String outputFilePath = config.getDownloadFolder() +
                File.separator +
                ((output != null) ? output : downloadedFile.getName());

        if (!downloadedFile.renameTo(new File(outputFilePath))) {
            System.out.println("An error occurred at trying to save the destination file");
            return 1;
        }

        System.out.println("Success");

        return 0;
    }

    private static String getTemporaryFilename() {
        return "mdl-download-" + System.currentTimeMillis() / 1000L;
    }
}