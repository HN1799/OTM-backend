package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Result;
import com.example.demo.entity.Student;
import com.example.demo.entity.Test;
import com.example.demo.service.StudentService;
import com.example.demo.service.TestService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    @Autowired
    private TestService testService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/tests")
    public ResponseEntity<List<TestDTO>> getTests() {
        List<TestDTO> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/tests/{testId}")
    public ResponseEntity<TestDTO> getTestById(
    		@PathVariable  Long testId){
    	TestDTO dto =	studentService.getTestById(testId);
    	return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/submitTest/{testId}")
    public ResponseEntity<Result> submitTest(
            @PathVariable Long testId,
            @RequestParam String rollNumber,
            @RequestBody List<String> answers) {

        Student student = studentService.getStudentByRollNumber(rollNumber);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        Result result = studentService.submitTest(testId, student, answers);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/results")
    public ResponseEntity<List<Result>> getResults(@RequestParam String rollNumber) {
        Student student = studentService.getStudentByRollNumber(rollNumber);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        List<Result> results = studentService.getResultsByStudent(student);
        return ResponseEntity.ok(results);
    }
}
