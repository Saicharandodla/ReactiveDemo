package com.nickolasfisher.webflux.Reactive.initialize;

import com.nickolasfisher.webflux.Reactive.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.nickolasfisher.webflux.Reactive.repository.DepartmentRepository;
import com.nickolasfisher.webflux.Reactive.repository.UserRepository;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;


@Component
@Profile("!test")
@Slf4j
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;



    @Override
    public void run(String... args){
        initalDataSetUp();
    }

    private List<User> getData(){
        return Arrays.asList(new User(null,"Suman Das",30,10000),
                new User(null,"Arjun Das",5,10000),
                new User(null,"subha dass",5,3000));

    }

    private void initalDataSetUp(){
        userRepository.deleteAll()
                .thenMany(Flux.fromIterable(getData())).
                flatMap(userRepository::save).
                thenMany(userRepository.findAll()).
                subscribe(user -> {
                    log.info("User Inserted from CommandLineRunner " + user);
                });
    }



}
