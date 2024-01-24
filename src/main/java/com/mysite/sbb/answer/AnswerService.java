package com.mysite.sbb.answer;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public void create(Question q, String content, SiteUser siteUser) {
		Answer a = new Answer();
		a.setContent(content);
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		a.setAuthor(siteUser);
		
		answerRepository.save(a);
	}

}
