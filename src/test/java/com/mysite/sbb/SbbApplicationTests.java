package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void contextLoads() {
	}

	@Transactional
	@Test
	void testJpa() {
		
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		
		List<Answer> answerList = oq.get().getAnswerList();
		for(Answer a:answerList) {
			System.out.println( a.getId()+ ", "+ a.getContent());
		}
		
		/*
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		
		Answer a = new Answer();
		a.setContent("sbb is just a small project");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(oq.get());
		this.answerRepository.save(a);
		*/
		
		/*
		assertEquals(3, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		this.questionRepository.delete(oq.get());
		assertEquals(2, this.questionRepository.count());
		*/
		
		/*
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("modified, what was sbb?" );
		this.questionRepository.save(q);
		*/
		
		/*
		List<Question> r = this.questionRepository.findBySubjectLike("%sbb%");
		assertEquals(2, r.size());
		for(Question q : r) {
			System.out.println( q.getId()+ ", " + q.getContent());
		}
		*/
		
		
		/*
		Question q = this.questionRepository.findBySubjectAndContent("q for spring boot", "what is spring boot");
		if( q!= null ) {
			System.out.println(q.getId());
			assertEquals(3, q.getId());
		}
		*/
		
		/*
		Question q = this.questionRepository.findBySubject("what is sbb");
		if( q != null ) {
			System.out.println(q.getContent());
			assertEquals("wanna know about sbb", q.getContent());
			
		}
		*/
		
		/*
		Optional<Question> oq = this.questionRepository.findById(1);
		if( oq.isPresent()) {
			Question q = oq.get();
			assertEquals("what is sbb", q.getSubject());
		}
		*/
		//https://wikidocs.net/160890
		
		/*
		List<Question> all = this.questionRepository.findAll();
		assertEquals(3, all.size());
		Question a = all.get(0);
		System.out.println(a.getContent() );
		*/
		
		/*
		Question q1 = new Question();
		q1.setSubject("what is sbb2");
		q1.setContent("wanna know about sbb2");
		q1.setCreateDate(LocalDateTime.now());
		
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("q for spring boot");
		q2.setContent("what is spring boot");
		
		this.questionRepository.save(q2);		
		*/
		
	}

}
