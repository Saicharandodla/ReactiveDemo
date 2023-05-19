package com.nickolasfisher.webflux.Reactive.repository;

import com.nickolasfisher.webflux.Reactive.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User,Integer> {
    @Query("select * from users where age>=$1")
    Flux<User> findByAge(int age);
}
