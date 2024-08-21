package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Result;
import com.example.demo.entity.Student;
import com.example.demo.entity.Test;
import com.example.demo.entity.TestAssignment;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.StudentService;
import com.example.demo.service.TestAssignmentService;
import com.example.demo.service.TestService;
import com.example.demo.service.impl.AdminService;
import com.example.demo.util.HelperUtil;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    @Autowired
    private TestService testService;

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private TestAssignmentService testAssignmentService;
    
    @Autowired
    private HelperUtil helperUtil;
    


    @PostMapping("/createTest")
    public ResponseEntity<TestDTO> createTest(@RequestBody TestDTO testDTO) {
        Test savedTest = testService.createTest(testDTO);
        TestDTO dto = helperUtil.mapTestToTestDto(savedTest);
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/assignTest")
    public ResponseEntity<String> assignTest(@RequestParam Long testId, @RequestParam Long studentId) {
        TestAssignment assignment = testAssignmentService.assignTest(testId, studentId);
        if (assignment == null) {
            return ResponseEntity.badRequest().body("Failed to assign test. Test or student not found.");
        }
        return ResponseEntity.ok("Test assigned successfully");
    }

    @GetMapping("/results")
    public ResponseEntity<List<Result>> getResultsByStudent(@RequestParam String rollNumber) {
        Student student = studentService.getStudentByRollNumber(rollNumber);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        List<Result> results = studentService.getResultsByStudent(student);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/average")
    public ResponseEntity<Double> getAverageScore(@RequestParam String rollNumber) {
        Student student = studentService.getStudentByRollNumber(rollNumber);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        double average = studentService.getAverageScore(student);
        return ResponseEntity.ok(average);
    }
    
    @PostMapping("/createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.ok(savedStudent);
    }
    
    @GetMapping("/getAllStudent")
    public ResponseEntity<List<Student>> getAllStudent(){
    	List<Student> allStudent=  studentService.getAllStudent();
    	return ResponseEntity.ok(allStudent);
    }
    
    
    @Autowired
    private AdminService adminService;
    @PostMapping("/create")
    public Admin createAdmin(@RequestBody AdminDto admin) {
        // You can add any additional business logic here (e.g., password encryption)
    	
    	Admin adminSaved = helperUtil.mapAdminDtoToAdmin(admin);
    	Admin save = adminService.createAdmin(adminSaved);
        return save;
    }

    
}
