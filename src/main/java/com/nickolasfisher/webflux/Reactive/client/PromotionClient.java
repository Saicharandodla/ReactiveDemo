package com.nickolasfisher.webflux.Reactive.client;

import com.nickolasfisher.webflux.Reactive.dto.Promotion;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PromotionClient {
    private final WebClient client;

    private final Promotion noPromotion = new Promotion ("non-promotion",0.0, LocalDate.of(29999,12,31));

    public PromotionClient(WebClient.Builder builder){
        this.client = builder.baseUrl("http://localhost:3000/promotions/").build();
    }

    public Mono<Promotion> getPromotion(Integer productId){
        return this.client.get().uri("{productId}",productId)
                .retrieve()
                .bodyToMono(Promotion.class).
                onErrorReturn(noPromotion);
    }
}
