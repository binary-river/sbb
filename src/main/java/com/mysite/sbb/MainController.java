package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/sbb")
	@ResponseBody
	public String sbb() {
		return "hello, welcome to sbb page!";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}

}
