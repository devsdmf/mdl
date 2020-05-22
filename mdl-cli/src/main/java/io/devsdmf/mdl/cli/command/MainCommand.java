package io.devsdmf.mdl.cli.command;

import picocli.CommandLine;

@CommandLine.Command (
    name = "main",
    description = "Test command"
)
public class MainCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello World!");
    }
}