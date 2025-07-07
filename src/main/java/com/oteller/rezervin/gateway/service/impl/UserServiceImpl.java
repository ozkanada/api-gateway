package com.oteller.rezervin.gateway.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.oteller.rezervin.gateway.controller.request.dto.UserCreate;
import com.oteller.rezervin.gateway.entity.Token;
import com.oteller.rezervin.gateway.entity.User;
import com.oteller.rezervin.gateway.exception.enums.FriendlyMessageCodes;
import com.oteller.rezervin.gateway.exception.exceptions.ActivationNotificationException;
import com.oteller.rezervin.gateway.exception.exceptions.EmailNotUniqueException;
import com.oteller.rezervin.gateway.exception.exceptions.InvalidTokenException;
import com.oteller.rezervin.gateway.exception.exceptions.UserNotFoundException;
import com.oteller.rezervin.gateway.repository.IUserRepository;
import com.oteller.rezervin.gateway.service.ITokenService;
import com.oteller.rezervin.gateway.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	private final IUserRepository userRepository;
	private final ITokenService tokenService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public UserServiceImpl(IUserRepository userRepository, ITokenService tokenService) {
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}
	
	@Override
	public User createUser(UserCreate userCreate) {
		User user = User.builder().username(userCreate.username()).email(userCreate.email())
				.password(passwordEncoder.encode(userCreate.password())).createdDate(new Date()).updatedDate(new Date())
				.deleted(false).firstName(userCreate.firstName()).lastName(userCreate.lastName()).activationToken(userCreate.activationToken()).build();
		User userResponse = null;
		try {
			userResponse = userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new EmailNotUniqueException(FriendlyMessageCodes.EMAIL_NOT_UNIQUE_EXCEPTION,
					"user request: " + userCreate.toString());
		} catch (MailException e) {
			throw new ActivationNotificationException(FriendlyMessageCodes.ACTIVATION_NOTIFICATION_EXCEPTION,
					"user request: " + userCreate.toString());
		}
		return userResponse;
	}

	@Override
	public User getUser(Long id) {
		User user = userRepository.getByUserIdAndDeletedFalse(id);
		if (user == null) {
			throw new UserNotFoundException(FriendlyMessageCodes.USER_NOT_FOUND_EXCEPTION, "user request: " + id);
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException(FriendlyMessageCodes.USER_NOT_FOUND_EXCEPTION, "user request: " + email);
		}
		return user;
	}
  

	@Override
	public User getUserByToken(String token) {
		Token tokenObj = tokenService.findByAccessToken(token);
		if(tokenObj == null) {
			throw new InvalidTokenException(FriendlyMessageCodes.INVALID_TOKEN_EXCEPTION, "user request: " + token);
		}
		return tokenObj.getUser();
	}


	@Override
	public User getUserByUserName(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException(FriendlyMessageCodes.USER_NOT_FOUND_EXCEPTION, "user request: " + username);
		}
		return user;
	}

}
