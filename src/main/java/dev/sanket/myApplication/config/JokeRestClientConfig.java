package dev.sanket.myApplication.config;

import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import javax.net.ssl.SSLContext;

@Configuration
@RequiredArgsConstructor
public class JokeRestClientConfig {
    private final RestClientInterceptor restClientInterceptor;

    @Bean
    public HttpComponentsClientHttpRequestFactory createRequestFactory() throws Exception {
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial((chain, authType) -> true)
                .build();
        SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslContext)
                .build();
        HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean
    public RestClient jokeRestClient(ObservationRegistry observationRegistry,
                                     HttpComponentsClientHttpRequestFactory requestFactory,
                                     @Value("${randomJoke.url}") String jokeBaseUrl) throws Exception {
        return RestClient.builder()
                .requestInterceptor(restClientInterceptor)
                .observationRegistry(observationRegistry)
                .requestFactory(new BufferingClientHttpRequestFactory(requestFactory))
                .baseUrl(jokeBaseUrl)
                .build();
    }

}
