package io.devsdmf.mdl.cli.configuration;

import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Loader {

    public static Map<String,Object> load(Path configurationFile) throws ConfigurationException {
        if (!Files.exists(configurationFile) || !Files.isReadable(configurationFile)) {
            throw new ConfigurationException("The specified configuration file could not be found or is not readable");
        }

        try {
            String content = new String(Files.readAllBytes(configurationFile));
            Yaml yaml = new Yaml();
            return yaml.load(content);
        } catch (IOException e) {
            throw new ConfigurationException("An error occurred at trying to read the specified configuration file",e);
        }
    }
}