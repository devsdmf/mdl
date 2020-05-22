package io.devsdmf.mdl.provider.twitter.api;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class ResponseHandler implements HttpClientResponseHandler<String> {

    private final Logger logger;

    public ResponseHandler() {
        this.logger = LoggerFactory.getLogger(ResponseHandler.class);
    }

    @Override
    public String handleResponse(ClassicHttpResponse res) throws HttpException, IOException {
        int status = res.getCode();

        if (status >= HttpStatus.SC_OK) {
            HttpEntity entity = res.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } else {
            logger.warn(String.format(
                    "Unexpected response received with status code %d", status
            ), res);

            return null;
        }
    }
}