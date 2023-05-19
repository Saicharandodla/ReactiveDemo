package com.nickolasfisher.webflux.Reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(appliesTo = "users")
public class User {
    @Id
    private Integer id;
    private String name;
    private int age;
    private double salary;
}
