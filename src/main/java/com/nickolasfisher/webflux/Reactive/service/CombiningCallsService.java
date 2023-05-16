package com.nickolasfisher.webflux.Reactive.service;

import com.nickolasfisher.webflux.Reactive.model.FirstCallDTO;
import com.nickolasfisher.webflux.Reactive.model.MergedCallsDTO;
import com.nickolasfisher.webflux.Reactive.model.SecondCallDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CombiningCallsService {

    private final WebClient serviceWebClient;

    public CombiningCallsService(@Qualifier("service-a-web-client") WebClient serviceWebClient){
        this.serviceWebClient = serviceWebClient;
    }

    public Mono<SecondCallDTO> sequentialCalls(Integer key){
        return this.serviceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/first/endpoint/{param}").build(key))
                        .retrieve()
                        .bodyToMono(FirstCallDTO.class).
                zipWhen(firstCallDTO -> serviceWebClient.get().uri(
                        uriBuilder->uriBuilder.path("/second/endpoint/{param}").build(firstCallDTO.getFieldFromFirstCall())).retrieve().bodyToMono(SecondCallDTO.class),((firstCallDTO, secondCallDTO) -> secondCallDTO));

    }

    public Mono<MergedCallsDTO> mergedCalls(Integer firstEndpointParam,Integer secondEndpointParam){

        Mono<FirstCallDTO> firstCallDTOMono = this.serviceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/first/endpoint/{param}").build(firstEndpointParam)).retrieve().bodyToMono(FirstCallDTO.class);

        Mono<SecondCallDTO> secondCallDTOMono = this.serviceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/second/endpont/{param}").build(secondEndpointParam)).retrieve().bodyToMono(SecondCallDTO.class);

        return Mono.zip(firstCallDTOMono,secondCallDTOMono).map(objects -> {
            MergedCallsDTO mergedCallsDTO = new MergedCallsDTO();

            mergedCallsDTO.setFieldOne(objects.getT1().getFieldFromFirstCall());
            mergedCallsDTO.setFieldTwo(objects.getT2().getFieldFromSecondCall());


          return mergedCallsDTO;

        });

    }
}
