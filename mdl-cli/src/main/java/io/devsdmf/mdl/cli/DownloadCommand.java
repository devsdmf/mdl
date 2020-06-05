package io.devsdmf.mdl.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import java.util.concurrent.Callable;

@Command()
public class DownloadCommand implements Callable<Integer> {

    @Parameters(paramLabel = "Media URL", description = "The URL of the media to be downloaded")
    private String url;

    @Option(
        names = {"-o", "--out"},
        paramLabel = "Output filename",
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

    private Logger logger;

    public DownloadCommand() {
        this.logger = LoggerFactory.getLogger(DownloadCommand.class);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Download command");
        return 0;
    }
}