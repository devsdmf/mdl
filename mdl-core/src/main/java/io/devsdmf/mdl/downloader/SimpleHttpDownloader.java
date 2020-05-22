package io.devsdmf.mdl.downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHttpDownloader implements Downloader {

    public File download(URL fileUrl, String outputFileName) throws IOException {
//        HttpURLConnection httpConnection = (HttpURLConnection) fileUrl.openConnection();

        // getting the file size
//        httpConnection.setRequestMethod("HEAD");
//        Long fileSize = httpConnection.getContentLengthLong();

        BufferedInputStream in = new BufferedInputStream(fileUrl.openStream());
        FileOutputStream out = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = in.read(buffer,0,1024)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        in.close();
        out.close();

        return new File(outputFileName);
    }
}