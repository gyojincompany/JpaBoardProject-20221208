package com.gyojincompany.gyojinboard.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//답변게시판 번호
	
	@Column(length = 1000)
	private String content;//답변게시판 내용
	
	private LocalDateTime createTime;
	
	private Question question;//질문게시판 객체(질문게시판의 id 획득)
	
}
