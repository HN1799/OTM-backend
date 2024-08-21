package com.example.demo.entity;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rollNumber;
    private String password;

//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private List<Result> results;

    // Getters and setters
}
