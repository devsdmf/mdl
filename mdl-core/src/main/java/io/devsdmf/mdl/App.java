package io.devsdmf.mdl;

import io.devsdmf.mdl.downloader.SimpleHttpDownloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class App {

    public static void main(String[] args) throws IOException {
        SimpleHttpDownloader downloader = new SimpleHttpDownloader();
        URL fileUrl = new URL("https://video.twimg.com/ext_tw_video/1261658208764125189/pu/vid/320x320/U4DKW1xCfu1orwRK.mp4?tag=10");
        String output = "/tmp/test.mp4";

        File result = downloader.download(fileUrl,output);
        System.out.println("Finished download: " + result.toString());
    }
}