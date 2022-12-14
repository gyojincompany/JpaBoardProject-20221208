package com.gyojincompany.gyojinboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyojincompany.gyojinboard.entity.SiteMember;
import com.gyojincompany.gyojinboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {	
	
	private final MemberRepository memberRepository;
	
	public SiteMember memberCreate(String username, String password, String email) {
		
		SiteMember member = new SiteMember();
		member.setUsername(username);
		member.setPassword(password);
		member.setEmail(email);
		
		memberRepository.save(member);
		
		return member;
		
	}

}
