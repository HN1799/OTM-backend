package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Student;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class LoginService {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    public String login(String username, String password) {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return "admin";
        }
        
        Optional<Student> student = studentRepository.findByName(username);
        if (student.isPresent() && student.get().getPassword().equals(password)) {
            return "student";
        }
        
        return "Invalid credentials";
    }
}

