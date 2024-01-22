package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	
	private final QuestionRepository questionRepository;
	
	public Page<Question> getList(int page) {
//		return this.questionRepository.findAll();
		Pageable pageable = PageRequest.of(page, 10);
		return this.questionRepository.findAll(pageable);
	}
	
	public Question getQuestion(Integer id){
		Optional<Question> oq = this.questionRepository.findById(id);
		if(!oq.isPresent()) throw new DataNotFoundException("question not found");
		return oq.get();
	}
	
	public void create(String subject, String content) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(q);
	}

}
