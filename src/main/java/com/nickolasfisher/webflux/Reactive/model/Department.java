package com.nickolasfisher.webflux.Reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(appliesTo = "department")
public class Department {
    @Id
    private Integer id;
    private String name;
    @Column(name = "user_id")
    private Integer userId;
    private String loc;
}
