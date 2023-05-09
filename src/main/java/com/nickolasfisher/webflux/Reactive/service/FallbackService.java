package com.nickolasfisher.webflux.Reactive.service;

import com.nickolasfisher.webflux.Reactive.model.WelcomeMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class FallbackService {

    private final WebClient serviceWebClient;

    public FallbackService(@Qualifier("service-a-web-client")
                           WebClient serviceWebClient) {
        this.serviceWebClient = serviceWebClient;
    }

    public Mono<WelcomeMessage> getWelcomeMessageByLocale(String locale){
        return (serviceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/locale/{locale}/message").build(locale))
                .retrieve()
                .bodyToMono(WelcomeMessage.class)
//                .onErrorReturn(throwable -> throwable instanceof WebClientResponseException) && ((WebClientResponseException)throwable).getStatusCode().
//                is5xxserverError(), new WelcomeMessage("hello fallback!")
    );

    }

}
