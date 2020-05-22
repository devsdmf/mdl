package io.devsdmf.mdl.cli;

import io.devsdmf.mdl.cli.command.MainCommand;
import picocli.CommandLine;

/**
 * Hello world!
 *
 */
public class Application
{
    public static void main(String[] args) {
        CommandLine.run(new MainCommand());
    }
}
