package io.devsdmf.mdl.cli.provider;

import io.devsdmf.mdl.cli.provider.extractor.Builder;
import io.devsdmf.mdl.cli.provider.extractor.BuilderException;
import io.devsdmf.mdl.provider.Extractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractorManagerTests {

    @Test
    public void testInitWithDefaultBuilders() {
        // when
        ExtractorManager em = new ExtractorManager();
        List<String> extractors = em.getExtractors();

        // then
        Assertions.assertEquals(1,extractors.size());
        Assertions.assertTrue(extractors.contains("twitter"));
    }

    @Test
    public void testInitWithoutDefaultBuilders() {
        // given
        Map<String,Builder> builders = new HashMap<>();

        // when
        ExtractorManager em = new ExtractorManager(builders);
        List<String> extractors = em.getExtractors();

        // then
        Assertions.assertEquals(0,extractors.size());
    }

    @Test
    public void testAddBuilder() {
        // mock
        Builder builder = mock(Builder.class);

        // given
        ExtractorManager em = new ExtractorManager(new HashMap<>());

        // when
        em.addBuilder("dummy",builder);

        // then
        Assertions.assertEquals(1, em.getExtractors().size());
    }

    @Test
    public void testBuild() throws ProviderException, BuilderException {
        // mock
        Extractor ex = mock(Extractor.class);
        Builder builder = mock(Builder.class);
        when(builder.build(any())).thenReturn(ex);

        // given
        ExtractorManager em = new ExtractorManager(new HashMap<>());
        em.addBuilder("dummy",builder);

        // when
        Extractor extractor = em.build("dummy",new HashMap<>());

        // then
        Assertions.assertNotNull(extractor);
    }

    @Test
    public void testBuildWithInvalidBuilder() {
        // given
        ExtractorManager em = new ExtractorManager(new HashMap<>());

        // when
        ProviderException ex = Assertions.assertThrows(
                ProviderException.class,
                () -> em.build("dummy",new HashMap<>())
        );

        // then
        Assertions.assertEquals("The specified provider does not exists or is not registered",ex.getMessage());
    }

    @Test
    public void testBuildWithFailureInBuilder() throws BuilderException {
        // mock
        Builder builder = mock(Builder.class);
        when(builder.build(any())).thenThrow(BuilderException.class);

        // given
        ExtractorManager em = new ExtractorManager(new HashMap<>());
        em.addBuilder("dummy",builder);

        // when
        ProviderException ex = Assertions.assertThrows(
                ProviderException.class,
                () -> em.build("dummy",new HashMap<>())
        );

        // then
        Assertions.assertEquals("An error occurred when trying to build the extractor, an exception was caught",ex.getMessage());
    }
}