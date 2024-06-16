package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Question;
import com.example.demo.entity.Test;

@Component
public class TestMapper {
    
    public QuestionDTO toQuestionDTO(Question question) {
        if (question == null) {
            return null;
        }

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setOptions(question.getOptions());
        questionDTO.setCorrectAnswer(question.getCorrectAnswer());

        return questionDTO;
    }

    public TestDTO toTestQuestTestDTO(Test savedTest) {
        if (savedTest == null) {
            return null;
        }

        TestDTO testDTO = new TestDTO();
        testDTO.setId(savedTest.getId() != null ? savedTest.getId().intValue() : null); // Assuming TestDTO id is Integer
        testDTO.setSubject(savedTest.getSubject());
        testDTO.setDuration(savedTest.getDuration());

        if (savedTest.getQuestions() != null) {
            List<QuestionDTO> questionDTOs = savedTest.getQuestions().stream()
                    .map(this::toQuestionDTO)
                    .collect(Collectors.toList());
            testDTO.setQuestions(questionDTOs);
        }

        return testDTO;
    }
}
