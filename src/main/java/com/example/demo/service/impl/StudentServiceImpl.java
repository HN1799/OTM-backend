package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Question;
import com.example.demo.entity.Result;
import com.example.demo.entity.Student;
import com.example.demo.entity.Test;
import com.example.demo.repository.ResultRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TestRepository;
import com.example.demo.service.StudentService;
import com.example.demo.util.HelperUtil;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private TestRepository testRepository;
    
    @Autowired
    private HelperUtil helperUtil;

    @Override
    public Student getStudentByRollNumber(String rollNumber) {
        return studentRepository.findByRollNumber(rollNumber).orElse(null);
    }

    @Override
    public Result submitTest(Long testId, Student student, List<String> answers) {
        Test test = testRepository.findById(testId).orElse(null);
        if (test == null) {
            return null;
        }
        TestDTO mapTestToTestDto = helperUtil.mapTestToTestDto(test);
        int score = calculateScore(mapTestToTestDto , answers);

        Result result = new Result();
//        result.setStudent(student);
//        result.setTest(test);
        result.setScore(score);

        return resultRepository.save(result);
    }

    @Override
    public List<Result> getResultsByStudent(Student student) {
        return resultRepository.findByStudent(student);
    }

    @Override
    public double getAverageScore(Student student) {
        List<Result> results = resultRepository.findByStudent(student);
        return results.stream().mapToInt(Result::getScore).average().orElse(0);
    }

    private int calculateScore(TestDTO mapTestToTestDto, List<String> answers) {
        int score = 0;
        List<QuestionDTO> questions = mapTestToTestDto.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getCorrectAnswer().equals(answers.get(i))) {
                score++;
            }
        }
        return score;
    }
    
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

	@Override
	public List<Student> getAllStudent() {
		studentRepository.findAll();
		return studentRepository.findAll();
	}

	@Override
	public TestDTO getTestById(Long testId) {
		// TODO Auto-generated method stub
		Optional<Test> byId = testRepository.findById(testId);
		TestDTO mapTestToTestDto = helperUtil.mapTestToTestDto(byId.get());
		return mapTestToTestDto;
	}
}
