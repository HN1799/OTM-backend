package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TestAssignment;

public interface TestAssignmentRepository extends JpaRepository<TestAssignment, Long> {
    // Additional methods if needed
}
