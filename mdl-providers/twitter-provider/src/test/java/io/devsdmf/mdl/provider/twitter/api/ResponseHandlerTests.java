package io.devsdmf.mdl.provider.twitter.api;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ResponseHandlerTests {

    @Test
    @Disabled("Needs to be fixed")
    public void test200OkResponseHandling() throws IOException, HttpException {
        // given
        ClassicHttpResponse response = mock(ClassicHttpResponse.class);
        when(response.getCode()).thenReturn(HttpStatus.SC_OK);
        when(response.getEntity()).thenReturn(mock(HttpEntity.class));

        HttpClientResponseHandler<String> handler = new ResponseHandler();

        // when
        String result = handler.handleResponse(response);

        // then
        Assertions.assertNotNull(result);
    }
}