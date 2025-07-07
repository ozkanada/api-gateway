package com.oteller.rezervin.gateway.service.impl;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.oteller.rezervin.gateway.configuration.AppUserDetails;
import com.oteller.rezervin.gateway.entity.User;
import com.oteller.rezervin.gateway.service.IUserService;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

	private final IUserService userService;

	public UserDetailsServiceImpl(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		User user = userService.getUserByUserName(username);
		if (user != null) {
			return Mono.just(new AppUserDetails(user));
		} else {
			return Mono.empty();
		}
	}

}