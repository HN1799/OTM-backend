package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Test;
import com.example.demo.mapper.TestMapper;

@Component
public class HelperUtil {
	
	@Autowired
	TestMapper mapper;

	public TestDTO mapTestToTestDto(Test savedTest) {
		return mapper.toTestQuestTestDTO(savedTest);
	}
	
	

}
