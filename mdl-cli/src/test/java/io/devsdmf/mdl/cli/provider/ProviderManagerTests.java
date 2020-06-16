package io.devsdmf.mdl.cli.provider;

import io.devsdmf.mdl.provider.Extractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import util.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ProviderManagerTests {

    @Test
    public void testInitWithoutProviders() {
        // given
        ProviderManager pm = new ProviderManager();

        // when
        List<String> providers = pm.getProviders();

        // then
        Assertions.assertEquals(0,providers.size());
    }

    @Test
    public void testInitFromConfiguration() throws IOException, ProviderException {
        // given
        Yaml yaml = new Yaml();
        Map<String,Object> baseConfig = yaml.load(Resource.getResourceFileAsString("test_config.yaml"));

        // when
        ProviderManager pm = ProviderManager.fromConfiguration(baseConfig);

        // then
        Assertions.assertEquals(1,pm.getProviders().size());
    }

    @Test
    public void testInitFromConfigurationInvalidProviders() throws IOException {
        // given
        Yaml yaml = new Yaml();
        Map<String,Object> baseConfig = yaml.load(Resource.getResourceFileAsString("test_config_without_providers.yaml"));

        // when
        ProviderException ex = Assertions.assertThrows(
                ProviderException.class,
                () -> ProviderManager.fromConfiguration(baseConfig)
        );

        // then
        Assertions.assertEquals("Could not found any provider configuration",ex.getMessage());
    }

    @Test
    public void testAddProvider() {
        // given
        ProviderManager pm = new ProviderManager();

        // when
        pm.addProvider("dummy",true,new HashMap<>());

        // then
        Assertions.assertEquals(1,pm.getProviders().size());
    }

    @Test
    public void testGetEnabledProviders() {
        // given
        ProviderManager pm = new ProviderManager();
        pm.addProvider("dummy1",true,new HashMap<>());
        pm.addProvider("dummy2",false,new HashMap<>());

        // when
        List<String> providers = pm.getEnabledProviders();

        // then
        Assertions.assertEquals(1,providers.size());
        Assertions.assertEquals("dummy1",providers.get(0));
    }

    @Test
    public void testGetExtractor() throws ProviderException {
        // mock
        Extractor ex = mock(Extractor.class);
        ExtractorManager em = mock(ExtractorManager.class);
        when(em.build(any(),any())).thenReturn(ex);

        // given
        ProviderManager pm = new ProviderManager(em);
        pm.addProvider("dummy",true,new HashMap<>());

        // when
        Extractor extractor = pm.getExtractor("dummy");

        // then
        Assertions.assertNotNull(extractor);
    }

    @Test
    public void testGetExtractorForDisabledProvider() throws ProviderException {
        // mock
        Extractor ex = mock(Extractor.class);
        ExtractorManager em = mock(ExtractorManager.class);
        when(em.build(any(),any())).thenReturn(ex);

        // given
        ProviderManager pm = new ProviderManager(em);
        pm.addProvider("dummy",false,new HashMap<>());

        // when
        ProviderException e = Assertions.assertThrows(
                ProviderException.class,
                () -> pm.getExtractor("dummy")
        );

        // then
        Assertions.assertEquals("Attempting to fetch disabled extractor, please check your configuration",e.getMessage());
    }
}