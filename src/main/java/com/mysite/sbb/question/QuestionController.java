package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionService questionService;
	
	@GetMapping("/question/list")
	public String list(Model model) {
		
		List<Question> questionList = this.questionService.getList(); 
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
	
	@GetMapping("/question/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer i) {
		
		/////
		Question q = this.questionService.getQuestion(i);
		model.addAttribute("question", q);
		return "question_detail";
	}
}
