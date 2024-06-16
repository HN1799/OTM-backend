package com.example.demo.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class TestAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Constructors, getters, and setters
}
