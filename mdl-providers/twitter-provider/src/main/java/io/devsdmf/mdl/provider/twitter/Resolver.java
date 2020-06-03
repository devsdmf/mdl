package io.devsdmf.mdl.provider.twitter;

import io.devsdmf.mdl.provider.twitter.api.resources.Tweet;
import java.net.URI;

public interface Resolver {

    public URI resolve(Tweet tweet) throws TwitterException;
}