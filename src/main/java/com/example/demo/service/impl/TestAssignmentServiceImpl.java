package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.entity.Test;
import com.example.demo.entity.TestAssignment;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TestAssignmentRepository;
import com.example.demo.repository.TestRepository;
import com.example.demo.service.TestAssignmentService;

@Service
public class TestAssignmentServiceImpl implements TestAssignmentService {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestAssignmentRepository testAssignmentRepository;

    @Override
    public TestAssignment assignTest(Long testId, Long studentId) {
        Test test = testRepository.findById(testId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (test == null || student == null) {
            return null; // Test or student not found
        }

        TestAssignment assignment = new TestAssignment();
        assignment.setTest(test);
        assignment.setStudent(student);

        return testAssignmentRepository.save(assignment);
    }

    // Other methods
}
