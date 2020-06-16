package io.devsdmf.mdl.cli.provider;

import io.devsdmf.mdl.cli.provider.extractor.Builder;
import io.devsdmf.mdl.cli.provider.extractor.BuilderException;
import io.devsdmf.mdl.cli.provider.extractor.TwitterBuilder;
import io.devsdmf.mdl.provider.Extractor;

import java.util.*;

public class ExtractorManager {

    private final Map<String,Builder> builders;

    public ExtractorManager() {
        this(getDefaultBuilders());
    }

    public ExtractorManager(Map<String,Builder> builders) {
        this.builders = builders;
    }

    public void addBuilder(String alias, Builder builder) {
        this.builders.put(alias,builder);
    }

    public List<String> getExtractors() {
        return new ArrayList<>(builders.keySet());
    }

    public Extractor build(String alias, Map<String,Object> options) throws ProviderException {
        if (!builders.containsKey(alias)) {
            throw new ProviderException("The specified provider does not exists or is not registered");
        }

        try {
            Builder builder = builders.get(alias);
            return builder.build(options);
        } catch (BuilderException e) {
            throw new ProviderException("An error occurred when trying to build the extractor, " +
                    "an exception was caught",e);
        }
    }

    private static Map<String,Builder> getDefaultBuilders() {
        Map<String,Builder> builders = new HashMap<>();

        builders.put("twitter",new TwitterBuilder());

        return builders;
    }
}