package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

	@NotEmpty(message = "username required")
	@Size(min=3, max=25)
	private String username;
	
	@NotEmpty(message = "password1 required")
	@Size(min=3, max=10)
	private String password1;

	@NotEmpty(message = "password2 required")
	@Size(min=3, max=10)
	private String password2;
	
	@NotEmpty(message = "email required!")
	@Email
	private String email;
}
