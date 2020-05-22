package io.devsdmf.mdl.downloader;

import java.io.File;
import java.net.URL;

public interface Downloader {

    public File download(URL url, String outputFileName) throws Exception;
}