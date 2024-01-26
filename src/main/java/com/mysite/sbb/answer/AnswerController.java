package com.mysite.sbb.answer;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model
			, @PathVariable("id") Integer id
			, @Valid AnswerForm answerForm
			, BindingResult bindingResult
			, Principal principal
			//, @RequestParam("content") String content
			) {
		
		Question q = questionService.getQuestion(id);
		
		if( bindingResult.hasErrors() ) {
			model.addAttribute("question", q);
			return "question_detail";
		}
		
		SiteUser siteUser = userService.getUser(principal.getName());
		Answer answer = answerService.create(q, answerForm.getContent(), siteUser);
		
		return String.format("redirect:/question/detail/%s#answer_%s", id, answer.getId());
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String answerModify(AnswerForm answerForm,
			                  @PathVariable("id") Integer id,
			                  Principal principal
			 ) {
		
		Answer answer = answerService.getAnswer(id);
		if( !answer.getAuthor().getUsername().equals(principal.getName()) ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unauthorized user");
		}
		
		answerForm.setContent(answer.getContent());

		return "answer_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String answerModify(
			@PathVariable("id") Integer id,
			@Valid AnswerForm answerForm,
			BindingResult bindingResult,
			Principal principal
			) {
		
		if( bindingResult.hasErrors()) {
			return "answer_form";
		}
		
		Answer a = answerService.getAnswer(id);
		
		if(!a.getAuthor().getUsername().equals(principal.getName()) ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unauthorized user");
		}
		
		answerService.modify(a, answerForm.getContent());
		
		return String.format("redirect:/question/detail/%s#answer_%s", a.getQuestion().getId(), a.getId());
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String answerDelete(
			@PathVariable("id") Integer id,
			Principal principal
			) {
		
		Answer answer = answerService.getAnswer(id);
		if(!principal.getName().equals(answer.getAuthor().getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unauthorized user");
		}
		
	
		answerService.delete(answer);
		
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String recommend(
			@PathVariable("id") Integer id,
			Principal principal
			) {
		
		Answer a = answerService.getAnswer(id);
		SiteUser u = userService.getUser(principal.getName());
		
		answerService.recommend(a, u);
		
		return String.format("redirect:/question/detail/%s#answer_%s", a.getQuestion().getId(), a.getId());
	}
}
