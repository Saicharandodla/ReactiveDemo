package com.nickolasfisher.webflux.Reactive.repository;

import com.nickolasfisher.webflux.Reactive.model.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DepartmentRepository extends ReactiveCrudRepository<Department,Integer> {
    Mono<Department> findByUserId(Integer userId);

}
