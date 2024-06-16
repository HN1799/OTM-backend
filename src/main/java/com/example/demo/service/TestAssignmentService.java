package com.example.demo.service;

import com.example.demo.entity.TestAssignment;

public interface TestAssignmentService {
    TestAssignment assignTest(Long testId, Long studentId);
    // Other methods
}
