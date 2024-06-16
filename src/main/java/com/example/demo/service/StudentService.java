package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Result;
import com.example.demo.entity.Student;

public interface StudentService {
    Student getStudentByRollNumber(String rollNumber);
    Result submitTest(Long testId, Student student, List<String> answers);
    List<Result> getResultsByStudent(Student student);
    double getAverageScore(Student student);
    Student createStudent(Student student);
	List<Student> getAllStudent();
	TestDTO getTestById(Long testId);

}
