package com.gyojincompany.gyojinboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gyojincompany.gyojinboard.dto.QuestionDto;
import com.gyojincompany.gyojinboard.entity.Question;
import com.gyojincompany.gyojinboard.repository.AnswerRepository;
import com.gyojincompany.gyojinboard.repository.QuestionRepository;
import com.gyojincompany.gyojinboard.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
//	@Autowired
//	private QuestionRepository questionRepository;
//	
//	@Autowired
//	private AnswerRepository answerRepository;
	
//	private final QuestionRepository questionRepository;
//	private final AnswerRepository answerRepository;
	
	private final QuestionService questionService; 
	
	@RequestMapping(value = "/")	
	public String home() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/index")	
	public String index() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")	
	public String list(Model model) {
		
//		List<Question> questionList = questionRepository.findAll();
		
		List<Question> questionList = questionService.getQuestionList();
		
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
	
	@RequestMapping(value = "/questionView/{id}")
	public String questionView(@PathVariable("id") Integer id) {
		
		
		return "question_view";
	}
}
