package com.mysite.sbb.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signUp(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	
	@PostMapping("/signup")
	public String signUp(Model model,
			@Valid UserCreateForm userCreateForm,
			BindingResult bindingResult
			) {
		
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordIncorrect", "password1 is not equal to password2");
			return "signup_form";
		}
		
		try {
			userService.create(userCreateForm.getUsername()
					, userCreateForm.getEmail()
					, userCreateForm.getPassword1());			
		} catch ( DataIntegrityViolationException e ) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "username already exists");
			return "signup_form";
		} catch ( Exception e ) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}
		
		return "redirect:/";
	}
	
}
