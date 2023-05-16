package com.nickolasfisher.webflux.Reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

}
