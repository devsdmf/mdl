package io.devsdmf.mdl.cli.provider;

import io.devsdmf.mdl.provider.Extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProviderManager {

    private final Map<String,Boolean> providerStatuses = new HashMap<>();

    private final Map<String,Object> providerOptions = new HashMap<>();

    private final ExtractorManager extractorManager;

    public ProviderManager() {
        this(new ExtractorManager());
    }

    public ProviderManager(ExtractorManager extractorManager) {
        this.extractorManager = extractorManager;
    }

    public void addProvider(String alias, Boolean enabled, Map<String,Object> options) {
        this.providerStatuses.put(alias,enabled);
        this.providerOptions.put(alias,options);
    }

    public List<String> getProviders() {
        return new ArrayList<>(providerStatuses.keySet());
    }

    public List<String> getEnabledProviders() {
        return providerStatuses.entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Boolean isProviderEnabled(String alias) {
        return providerStatuses.getOrDefault(alias,false);
    }

    public Extractor getExtractor(String provider) throws ProviderException {
        if (!isProviderEnabled(provider)) {
            throw new ProviderException("Attempting to fetch disabled extractor, please check your configuration");
        }

        Map<String,Object> options = (Map<String,Object>)providerOptions.getOrDefault(provider,new HashMap<>());

        return extractorManager.build(provider,options);
    }

    public static ProviderManager fromConfiguration(Map<String,Object> configuration) throws ProviderException {
        ProviderManager manager = new ProviderManager();

        if (configuration.containsKey("providers")) {
            Map<String,Object> providerConfiguration = (Map<String,Object>)configuration.get("providers");

            for (Map.Entry<String, Object> entry: providerConfiguration.entrySet()) {
                Map<String,Object> config = (Map<String,Object>) entry.getValue();
                Boolean status = (Boolean)config.get("enabled");
                Map<String,Object> options = (Map<String,Object>)config.get("options");

                manager.addProvider(entry.getKey(),status,options);
            }
        } else {
            throw new ProviderException("Could not found any provider configuration");
        }

        return manager;
    }
}
