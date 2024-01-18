package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa() {
		
		
		
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
