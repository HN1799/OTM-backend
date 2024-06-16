package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Question;
import com.example.demo.entity.Test;
import com.example.demo.repository.TestRepository;
import com.example.demo.service.TestService;
import com.example.demo.util.HelperUtil;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

   @Autowired
   HelperUtil helperUtil;
   
    @Override
    public Test createTest(TestDTO testDTO) {
        Test test = new Test();
        test.setSubject(testDTO.getSubject());
        test.setDuration(testDTO.getDuration());

        List<Question> questions = new ArrayList<>();
        for (QuestionDTO questionDTO : testDTO.getQuestions()) {
            Question question = new Question();
            question.setQuestionText(questionDTO.getQuestionText());
            question.setOptions(questionDTO.getOptions());
            question.setCorrectAnswer(questionDTO.getCorrectAnswer());
            question.setTest(test);
            questions.add(question);
        }
        test.setQuestions(questions);

        return testRepository.save(test);
    }


    @Override
    public List<TestDTO> getAllTests() {
    	List<Test> all = testRepository.findAll();
    	List<TestDTO> allDto = new ArrayList<>();
    	for (Test test : all) {
    		TestDTO mapTestToTestDto = helperUtil.mapTestToTestDto(test);
    		allDto.add(mapTestToTestDto);
		}
        return allDto;
    }

    @Override
    public Test getTestById(Long id) {
        return testRepository.findById(id).orElse(null);
    }
}
