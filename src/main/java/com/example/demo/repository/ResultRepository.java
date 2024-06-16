package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Result;
import com.example.demo.entity.Student;

public interface ResultRepository extends JpaRepository<Result, Long> {
	List<Result> findByStudent(Student student);
}
