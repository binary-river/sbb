package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/question")
@Controller
public class QuestionController {

	private final QuestionService questionService;
	
	@GetMapping("/list")
	public String list(Model model
			, @RequestParam(value="page", defaultValue="0") int page) {
		
		Page<Question> paging = this.questionService.getList(page); 
		model.addAttribute("paging", paging);
		
		return "question_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model,
			@PathVariable("id") Integer i,
			AnswerForm answerForm
			) {
		
		Question q = this.questionService.getQuestion(i);
		model.addAttribute("question", q);
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String create(Model model, QuestionForm questionForm) {
		return "question_form";
	}
	
	
	@PostMapping("/create")
	public String create(Model model,
			@Valid QuestionForm questionForm, 
			BindingResult bindingResult ) {
		
		if(bindingResult.hasErrors()) return "question_form";
		
		questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";
	}
}
