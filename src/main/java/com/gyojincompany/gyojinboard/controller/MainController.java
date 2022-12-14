package com.gyojincompany.gyojinboard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gyojincompany.gyojinboard.dto.AnswerForm;
import com.gyojincompany.gyojinboard.dto.MemberForm;
import com.gyojincompany.gyojinboard.dto.QuestionDto;
import com.gyojincompany.gyojinboard.dto.QuestionForm;
import com.gyojincompany.gyojinboard.entity.Question;
import com.gyojincompany.gyojinboard.repository.AnswerRepository;
import com.gyojincompany.gyojinboard.repository.QuestionRepository;
import com.gyojincompany.gyojinboard.service.AnswerService;
import com.gyojincompany.gyojinboard.service.MemberService;
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
	private final AnswerService answerService; 
	private final MemberService memberService;
	
	@RequestMapping(value = "/")	
	public String home() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/index")	
	public String index() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")	
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		
//		List<Question> questionList = questionRepository.findAll();
		
//		List<QuestionDto> questionList = questionService.getQuestionList();
		
		Page<Question> paging = questionService.getList(page);
		
		model.addAttribute("paging", paging);
		
		return "question_list";
	}
	
	@RequestMapping(value = "/questionView/{id}")
	public String questionView(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		
		QuestionDto question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_view";
	}
	
	@PostMapping(value = "/answerCreate/{id}")
	public String answerCreate(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
		
		QuestionDto questionDto = questionService.getQuestion(id);
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("question", questionDto);
			return "question_view";
		}
		
		answerService.answerCreate(answerForm.getContent(), id);
				
		return String.format("redirect:/questionView/%s", id);
	}
	

	@RequestMapping(value = "/question_form")
	public String questionCreate(QuestionForm questionForm) {
				
		return "question_form";
	}
	
	@PostMapping(value = "/questionCreate")
	public String questionCreateOk(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		questionService.questionCreate(questionForm.getSubject(), questionForm.getContent());
				
		return "redirect:list";
	}
	
	@RequestMapping(value = "/join")
	public String join(MemberForm memberForm) {
				
		return "join_form";
	}
	
	@PostMapping(value = "/joinOk")
	public String joinOk(@Valid MemberForm memberForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "join_form";
		}
		
		
		try {
			memberService.memberCreate(memberForm.getUsername(), memberForm.getPassword(), memberForm.getEmail());
		}catch(Exception e){
			e.printStackTrace();
			bindingResult.reject("joinFail", "이미 등록된 아이디입니다.");
			return "join_form";
		}
		return "redirect:list";
	}
	
}
