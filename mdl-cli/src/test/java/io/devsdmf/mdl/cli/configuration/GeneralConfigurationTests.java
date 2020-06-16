package io.devsdmf.mdl.cli.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import util.Resource;
import java.io.IOException;
import java.util.Map;

public class GeneralConfigurationTests {

    @Test
    public void testFactoryWithValidConfiguration() throws IOException, ConfigurationException {
        // given
        Yaml yaml = new Yaml();
        Map<String,Object> baseConfig = yaml.load(Resource.getResourceFileAsString("test_config.yaml"));

        // when
        GeneralConfiguration config = GeneralConfiguration.factory(baseConfig);

        // then
        Assertions.assertNotNull(config.getTempFolder());
        Assertions.assertNotNull(config.getDownloadFolder());
    }

    @Test
    public void testFactoryWithInvalidGeneralSection() throws IOException {
        // given
        Yaml yaml = new Yaml();
        Map<String,Object> baseConfig = yaml.load(Resource.getResourceFileAsString("test_config_without_general.yaml"));

        // when
        ConfigurationException ex = Assertions.assertThrows(
                ConfigurationException.class,
                () -> GeneralConfiguration.factory(baseConfig)
        );

        // then
        Assertions.assertEquals("The specified configuration file does not have a general section",ex.getMessage());
    }

    @Test
    public void testFactoryWithInvalidTemporaryFolder() throws IOException {
        // given
        Yaml yaml = new Yaml();
        Map<String,Object> baseConfig = yaml.load(Resource.getResourceFileAsString("test_config_invalid_temp.yaml"));

        // when
        ConfigurationException ex = Assertions.assertThrows(
                ConfigurationException.class,
                () -> GeneralConfiguration.factory(baseConfig)
        );

        // then
        Assertions.assertEquals("The specified temporary directory does not exists or is not writeable",ex.getMessage());
    }

    @Test
    public void testFactoryWithInvalidDownloadFolder() throws IOException {
        // given
        Yaml yaml = new Yaml();
        Map<String,Object> baseConfig = yaml.load(Resource.getResourceFileAsString("test_config_invalid_download.yaml"));

        // when
        ConfigurationException ex = Assertions.assertThrows(
                ConfigurationException.class,
                () -> GeneralConfiguration.factory(baseConfig)
        );

        // then
        Assertions.assertEquals("The specified download directory does not exists or is not writeable",ex.getMessage());
    }
}