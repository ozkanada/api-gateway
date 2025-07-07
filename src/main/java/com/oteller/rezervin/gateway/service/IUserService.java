package com.oteller.rezervin.gateway.service;

import com.oteller.rezervin.gateway.controller.request.dto.UserCreate;
import com.oteller.rezervin.gateway.entity.User;

public interface IUserService {
	
	User createUser(UserCreate userCreate);

	User getUser(Long id);

	User findByEmail(String email);

	User getUserByToken(String token);

	User getUserByUserName(String username);
}
