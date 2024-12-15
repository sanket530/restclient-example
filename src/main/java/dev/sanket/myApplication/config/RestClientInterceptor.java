package dev.sanket.myApplication.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class RestClientInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        var response = execution.execute(request, body);
        logResponse(request, response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        if (log.isDebugEnabled()) {
            log.debug("===========================request begin================================================");
            log.debug("URI         : {}", request.getURI());
            log.debug("Method      : {}", request.getMethod());
            log.debug("Headers     : {}", request.getHeaders());
            log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
            log.debug("==========================request end================================================");
        }
    }

    private void logResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
        if (log.isDebugEnabled()) {
            log.debug("============================response begin==========================================");
            log.debug("Status code  : {}", response.getStatusCode());
            log.debug("Status text  : {}", response.getStatusText());
            log.debug("Headers      : {}", response.getHeaders());
            log.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
            log.debug("=======================response end=================================================");
        }
    }

    private static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {
        private final ClientHttpResponse response;
        private final byte[] body;

        public BufferingClientHttpResponseWrapper(ClientHttpResponse response,
                                                  byte[] body) {
            this.response = response;
            this.body = body;
        }

        @Override
        public InputStream getBody() {
            return new ByteArrayInputStream(body);
        }

        // Delegate other methods to wrapped response
        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return response.getStatusCode();
        }

        @Override
        public String getStatusText() {
            return "";
        }

        @Override
        public void close() {

        }

        @Override
        public HttpHeaders getHeaders() {
            return response.getHeaders();
        }
    }
}
