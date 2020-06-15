package io.devsdmf.mdl.cli.provider.extractor;

import io.devsdmf.mdl.provider.Extractor;
import io.devsdmf.mdl.provider.twitter.TwitterExtractor;
import io.devsdmf.mdl.provider.twitter.api.ApiClient;
import io.devsdmf.mdl.provider.twitter.api.auth.BearerTokenCredentials;

import java.util.Map;

public class TwitterBuilder implements Builder {

    @Override
    public Extractor build(Map<String, Object> options) throws BuilderException {
        if (!options.containsKey("accessToken")) {
            throw new BuilderException("Missing access token configuration in twitter provider");
        }

        BearerTokenCredentials credentials = new BearerTokenCredentials((String)options.get("accessToken"));
        return new TwitterExtractor(new ApiClient(),credentials);
    }
}