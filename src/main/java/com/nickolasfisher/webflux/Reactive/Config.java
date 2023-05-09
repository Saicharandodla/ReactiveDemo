package com.nickolasfisher.webflux.Reactive;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class Config {

    @Bean("service-a-web-client")
    public WebClient serviceAWebClient() {
        HttpClient httpClient = HttpClient.create().tcpConfiguration(tcpClient ->
                tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                        .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS)))
        );

        return WebClient.builder()
                .baseUrl("http://your-base-url.com")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
