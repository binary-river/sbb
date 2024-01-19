package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.sbb.question.Question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public void create(Question q, String content) {
		Answer a = new Answer();
		a.setContent(content);
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		
		answerRepository.save(a);
	}

}
