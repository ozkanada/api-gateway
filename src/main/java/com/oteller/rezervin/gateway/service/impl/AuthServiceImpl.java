package com.oteller.rezervin.gateway.service.impl;

import java.util.UUID;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import com.oteller.rezervin.gateway.configuration.AppUserDetails;
import com.oteller.rezervin.gateway.configuration.JwtUtil;
import com.oteller.rezervin.gateway.controller.request.AuthRequest;
import com.oteller.rezervin.gateway.controller.request.UserRegisterRequest;
import com.oteller.rezervin.gateway.controller.request.dto.UserCreate;
import com.oteller.rezervin.gateway.controller.response.AuthResponse;
import com.oteller.rezervin.gateway.controller.response.dto.TokenDTO;
import com.oteller.rezervin.gateway.entity.User;
import com.oteller.rezervin.gateway.service.IAuthService;
import com.oteller.rezervin.gateway.service.ITokenService;
import com.oteller.rezervin.gateway.service.IUserService;
import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements IAuthService {

	private final JwtUtil jwtUtil;
	private final ITokenService tokenService;
	private final IUserService userService;

	public AuthServiceImpl(JwtUtil jwtUtil, ITokenService tokenService, IUserService userService) {
		this.jwtUtil = jwtUtil;
		this.tokenService = tokenService;
		this.userService = userService;
	}

	@Override
	public Mono<AuthResponse> authenticate(AuthRequest authRequest) {
		User user = userService.findByEmail(authRequest.email());
		AppUserDetails userDetails = new AppUserDetails(user);
		String accessToken = jwtUtil.generateAccessToken(userDetails);
		String refreshToken = jwtUtil.generateRefreshToken(userDetails);
		tokenService
				.saveToken(TokenDTO.builder().user(user).accessToken(accessToken).refreshToken(refreshToken).build());
		return Mono.just(new AuthResponse(accessToken, refreshToken));
	}

	public void logout(String authorizationHeader) {
		jwtUtil.logout(authorizationHeader);
	}

	@Transactional(rollbackOn = MailException.class)
	@Override
	public Mono<AuthResponse> registerUser(UserRegisterRequest req) {
		String activationToken = UUID.randomUUID().toString();
		UserCreate userCreate = new UserCreate(req.username(), req.email(), req.firstName(), req.lastName(),activationToken, req.password());
		User user = userService.createUser(userCreate);
		AppUserDetails userDetails = new AppUserDetails(user);
		String accessToken = jwtUtil.generateAccessToken(userDetails);
		String refreshToken = jwtUtil.generateRefreshToken(userDetails);
		tokenService.saveToken(TokenDTO.builder().user(user).accessToken(accessToken).refreshToken(refreshToken).build());
		return Mono.just(new AuthResponse(accessToken, refreshToken));
	}

}
