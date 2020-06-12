package io.devsdmf.mdl.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

final public class ExtractorManager {

    private final Map<String,String> aliases;

    public ExtractorManager(Map<String,String> aliases) {
        this.aliases = aliases;
    }

    public ExtractorManager() {
        this.aliases = new HashMap();

        aliases.put("twitter","io.devsdmf.mdl.provider.twitter.TwitterExtractor");
    }

    public void addProvider(String alias, String className) {
        this.aliases.put(alias,className);
    }

    public Map<String,String> getProviders() {
        return aliases;
    }

    public Optional<String> getClassFor(String alias) {
        if (this.aliases.containsKey(alias)) {
            return Optional.of(this.aliases.get(alias));
        }

        return Optional.empty();
    }
}