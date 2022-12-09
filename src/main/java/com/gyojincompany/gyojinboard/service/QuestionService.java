package com.gyojincompany.gyojinboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyojincompany.gyojinboard.dto.QuestionDto;
import com.gyojincompany.gyojinboard.entity.Question;
import com.gyojincompany.gyojinboard.repository.AnswerRepository;
import com.gyojincompany.gyojinboard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	
	public List<Question> getQuestionList() {
		List<Question> questionList = questionRepository.findAll();

		
		return questionList;
	}
	
	public Question getQuestion() {
		
		
		
		
		return question;
	}
	
}
