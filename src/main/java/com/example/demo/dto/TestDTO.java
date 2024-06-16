package com.example.demo.dto;

import java.util.List;

import lombok.Data;
@Data
public class TestDTO {
	private Integer id;
    private String subject;
    private List<QuestionDTO> questions;
    private String duration;

    // Getters and setters
   
}
