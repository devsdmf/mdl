package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Resource {

    public static String getResourceFileAsString(String filename) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream in = classLoader.getResourceAsStream(filename)) {
            if (in == null) return null;
            try (InputStreamReader reader = new InputStreamReader(in)) {
                BufferedReader buffer = new BufferedReader(reader);
                return buffer.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}