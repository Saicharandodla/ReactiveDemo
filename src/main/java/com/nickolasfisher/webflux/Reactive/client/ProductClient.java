package com.nickolasfisher.webflux.Reactive.client;

import com.nickolasfisher.webflux.Reactive.dto.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(WebClient.Builder builder){
 this.webClient = builder.baseUrl("http://localhost:3000/products/").build();
    }
    public Mono<Product> getProduct(Integer productId){

        return this.webClient.get().uri("{productId}",productId)
                .retrieve().bodyToMono(Product.class)
                .onErrorResume(ex->Mono.empty());
    }
}
