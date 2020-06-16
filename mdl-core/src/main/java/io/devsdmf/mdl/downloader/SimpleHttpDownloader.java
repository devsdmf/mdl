package io.devsdmf.mdl.downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleHttpDownloader implements Downloader {

    public File download(URL fileUrl, String outputFileName) throws IOException, DownloaderException {
//        HttpURLConnection httpConnection = (HttpURLConnection) fileUrl.openConnection();

        // getting the file size
//        httpConnection.setRequestMethod("HEAD");
//        Long fileSize = httpConnection.getContentLengthLong();

        String ext = getFileExtension(fileUrl.getPath());
        String outputFile = outputFileName + "." + ext;

        BufferedInputStream in = new BufferedInputStream(fileUrl.openStream());
        FileOutputStream out = new FileOutputStream(outputFile);

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = in.read(buffer,0,1024)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        in.close();
        out.close();

        return new File(outputFile);
    }

    private static String getFileExtension(String path) throws DownloaderException {
        Pattern p = Pattern.compile("\\.([a-zA-Z0-9]+)");
        Matcher m = p.matcher(path);

        if (!m.find() || m.groupCount() == 0) {
            throw new DownloaderException("Could not resolve file extension for path: " + path);
        }

        return m.group(1);
    }
}