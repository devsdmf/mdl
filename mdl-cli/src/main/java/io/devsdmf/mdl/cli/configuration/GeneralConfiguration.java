package io.devsdmf.mdl.cli.configuration;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class GeneralConfiguration {

    private static final String DEFAULT_TEMPORARY_FOLDER = "/tmp/";
    private static final String DEFAULT_DOWNLOAD_FOLDER = "/tmp/";

    private Path tempFolderPath;

    private Path downloadFolderPath;

    public void setTempFolder(String folderPath) throws ConfigurationException {
        Path p = FileSystems.getDefault().getPath(resolveUserHomeDirectory(folderPath)).toAbsolutePath();

        if (isDirectoryValid(p)) {
            tempFolderPath = p;
        } else {
            throw new ConfigurationException("The specified temporary directory does not exists or is not writeable");
        }
    }

    public Path getTempFolder() {
        return tempFolderPath;
    }

    public void setDownloadFolder(String folderPath) throws ConfigurationException {
        Path p = FileSystems.getDefault().getPath(resolveUserHomeDirectory(folderPath)).toAbsolutePath();

        if (isDirectoryValid(p)) {
            downloadFolderPath = p;
        } else {
            throw new ConfigurationException("The specified download directory does not exists or is not writeable");
        }
    }

    public Path getDownloadFolder() {
        return downloadFolderPath;
    }

    private String resolveUserHomeDirectory(String path) {
        return path.replaceFirst("^~",System.getProperty("user.home"));
    }

    private Boolean isDirectoryValid(Path path) {
        return Files.exists(path) && Files.isWritable(path);
    }

    public static GeneralConfiguration factory(Map<String,Object> config) throws ConfigurationException {
        if (!config.containsKey("general")) {
            throw new ConfigurationException("The specified configuration file does not have a general section");
        }

        GeneralConfiguration c = new GeneralConfiguration();

        Map<String,Object> general = (Map<String, Object>) config.get("general");
        if (general.containsKey("folders")) {
            Map<String,Object> folders = (Map<String, Object>) general.get("folders");

            if (folders.containsKey("temp")) {
                String temporaryFolder = (String) folders.get("temp");

                if (temporaryFolder != null && !temporaryFolder.equals("~")) {
                    c.setTempFolder(temporaryFolder);
                } else {
                    c.setTempFolder(DEFAULT_TEMPORARY_FOLDER);
                }
            } else {
                c.setTempFolder(DEFAULT_TEMPORARY_FOLDER);
            }

            if (folders.containsKey("download")) {
                String downloadFolder = (String) folders.get("download");

                if (downloadFolder != null && !downloadFolder.equals("~")) {
                    c.setDownloadFolder(downloadFolder);
                } else {
                    c.setDownloadFolder(DEFAULT_DOWNLOAD_FOLDER);
                }
            } else {
                c.setDownloadFolder(DEFAULT_DOWNLOAD_FOLDER);
            }
        } else {
            c.setTempFolder(DEFAULT_TEMPORARY_FOLDER);
            c.setDownloadFolder(DEFAULT_DOWNLOAD_FOLDER);
        }

        return c;
    }
}