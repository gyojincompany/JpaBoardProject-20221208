package com.gyojincompany.gyojinboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gyojincompany.gyojinboard.entity.Answer;
import com.gyojincompany.gyojinboard.entity.Question;
import com.gyojincompany.gyojinboard.entity.SiteMember;
import com.gyojincompany.gyojinboard.repository.AnswerRepository;
import com.gyojincompany.gyojinboard.repository.MemberRepository;
import com.gyojincompany.gyojinboard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	private final MemberService memberService;
	
	public void answerCreate(String content, Integer id, String username) {
		
		Optional<Question> optQuestion = questionRepository.findById(id);
		Question question = optQuestion.get();
		
		SiteMember siteMember = memberService.getMemberInfo(username);
		
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateTime(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setWriter(siteMember);
		
		answerRepository.save(answer);
		
	}
}
