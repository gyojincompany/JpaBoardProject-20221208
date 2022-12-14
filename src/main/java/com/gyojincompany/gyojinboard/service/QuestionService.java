package com.gyojincompany.gyojinboard.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gyojincompany.gyojinboard.dto.QuestionDto;
import com.gyojincompany.gyojinboard.entity.Question;
import com.gyojincompany.gyojinboard.exception.DataNotFoundException;
import com.gyojincompany.gyojinboard.repository.AnswerRepository;
import com.gyojincompany.gyojinboard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	
	public Page<Question> getList(int page) {
		
		List<Sort.Order> sort = new ArrayList<>();
		
		sort.add(Sort.Order.desc("id"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));//페이지당 표시되는 글 개수
		
		Page<Question> pages = questionRepository.findAll(pageable);
		
		return pages;
		
	}
	
	public List<QuestionDto> getQuestionList() {
		
		List<Question> questionList = questionRepository.findAll();
		
		
		List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
		
		
		for(int i=0;i<questionList.size();i++) {
			//System.out.println("list인덱스:"+i);
			Question question = questionList.get(i);
			QuestionDto questionDto = new QuestionDto();
			
			questionDto.setId(question.getId());
			questionDto.setContent(question.getContent());
			questionDto.setSubject(question.getSubject());
			questionDto.setAnswers(question.getAnswerList());
			questionDto.setCreateDate(question.getCreateDate());
			
			questionDtos.add(questionDto);
		}
		
		
		return questionDtos;
	}
	
	public QuestionDto getQuestion(Integer id) {
		
		Optional<Question> optQuestion = questionRepository.findById(id);
		
		QuestionDto questionDto = new QuestionDto();
		if(optQuestion.isPresent()) {
			Question question = optQuestion.get();
			questionDto.setId(question.getId());
			questionDto.setContent(question.getContent());
			questionDto.setSubject(question.getSubject());
			questionDto.setAnswers(question.getAnswerList());
			questionDto.setCreateDate(question.getCreateDate());
			return questionDto;
		} else {
			throw new DataNotFoundException("해당 질문이 없습니다.");
		}		
	}
	
	public void questionCreate(String subject, String content) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(question);
	}
	
}
