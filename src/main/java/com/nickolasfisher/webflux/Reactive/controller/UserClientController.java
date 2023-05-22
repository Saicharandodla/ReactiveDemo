package com.nickolasfisher.webflux.Reactive.controller;

import com.nickolasfisher.webflux.Reactive.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.nickolasfisher.webflux.Reactive.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client/users")
public class UserClientController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable String userId){
        Mono<User> user = userClient.getUser(userId);
        return user.map(u->ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<User> getAllUsers(){
            return userClient.getAllUsers();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create (@RequestBody User user){
        return userClient.createUser(user);
    }
}
