package com.example.demo.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

   
    @JoinColumn(name = "student_id")
    private Long student;

    @JoinColumn(name = "test_id")
    private Long test;

    private int score;

    // Getters and setters
}
