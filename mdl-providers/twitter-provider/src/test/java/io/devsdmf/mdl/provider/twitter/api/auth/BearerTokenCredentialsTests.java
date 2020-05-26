package io.devsdmf.mdl.provider.twitter.api.auth;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ProtocolException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class BearerTokenCredentialsTests {

    @Test
    public void testApplyHeaderToHttpMessage() throws URISyntaxException, ProtocolException {
        // given
        String accessToken = "F00T0k3n";
        BearerTokenCredentials credentials = new BearerTokenCredentials(accessToken);
        HttpPost req = new HttpPost(new URI("https://www.devsdmf.io"));

        // when
        req = (HttpPost) credentials.apply(req);

        // then
        Assertions.assertEquals("Bearer " + accessToken,
                req.getHeader(HttpHeaders.AUTHORIZATION).getValue());
    }
}