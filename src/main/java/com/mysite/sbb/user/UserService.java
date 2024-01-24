package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
	
		return userRepository.save(user);
	}
	
	public SiteUser getUser(String username) {
		Optional<SiteUser> _siteUser = this.userRepository.findByUsername(username);
		
		if( _siteUser.isPresent() ) {
			return _siteUser.get();
		} else {
			throw new DataNotFoundException("siteuser not found");
		}
	}

}
