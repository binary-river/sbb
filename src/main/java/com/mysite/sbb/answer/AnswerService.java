package com.mysite.sbb.answer;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
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
	
	public Answer getAnswer(Integer id) {
		Optional<Answer> oa = answerRepository.findById(id);
		if(oa.isPresent()) {
			return oa.get();
		} else {
			throw new DataNotFoundException("answer not found");
		}
	}
	
	public void modify(Answer answer, String content) {
		Answer a = getAnswer(answer.getId());
		a.setContent(content);
		a.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	
	public void delete(Answer answer) {
		answerRepository.delete(answer);
	}

}
