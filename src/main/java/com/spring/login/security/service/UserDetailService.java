package com.spring.login.security.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.login.model.User;
import com.spring.login.repository.UserRepository;
@Service
public class UserDetailService implements UserDetailsService {
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailImpl.build(user);
	}

	public User registerUser(User user) {
		return userRepository.save(user);

	}

}
