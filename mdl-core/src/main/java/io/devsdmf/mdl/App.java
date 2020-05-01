package io.devsdmf.mdl;

import java.net.MalformedURLException;
import java.net.URL;

public class App
{
    public static void main(String[] args)
    {
        try {
//            String rawUrl = args[0];
            String rawUrl = "https://twitter.com/devsdmf/status/1253042706391203845?s=20";
            URL url = new URL(rawUrl);
            System.out.println("URL => " + rawUrl);

            TwitterExtractor te = new TwitterExtractor("foo");
            te.extract(url);

        } catch (MalformedURLException e) {
            System.out.println(">>>>>> ERROR!");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(">>>>>> ERROR!!!");
            System.out.println(e.getMessage());
        }

    }
}
