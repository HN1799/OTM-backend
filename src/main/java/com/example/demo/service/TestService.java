package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Test;

public interface TestService {
	  Test createTest(TestDTO testDTO);
    List<TestDTO> getAllTests();
    Test getTestById(Long id);
}
