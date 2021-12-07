package com.devsuperior.movieflix.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		var user = repository.findByEmail(username);
		if (user == null) {
			logger.error("User not found for email " + username);
			throw new UsernameNotFoundException("User not found for email" + username);
		}
		logger.info("User found for email " + username);
		return user;
	}

	public UserDTO getUserInfo() {
		var user = authService.authenticated();
		return new UserDTO(user);
	}

}
