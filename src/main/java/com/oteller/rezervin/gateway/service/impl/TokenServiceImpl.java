package com.oteller.rezervin.gateway.service.impl;

import org.springframework.stereotype.Service;
import com.oteller.rezervin.gateway.controller.response.dto.TokenDTO;
import com.oteller.rezervin.gateway.entity.Token;
import com.oteller.rezervin.gateway.repository.ITokenRepository;
import com.oteller.rezervin.gateway.service.ITokenService;

@Service
public class TokenServiceImpl implements ITokenService {

	private final ITokenRepository tokenRepository;

	public TokenServiceImpl(ITokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@Override
	public void saveToken(TokenDTO tokenDTO) {
		Token token = Token.builder().accessToken(tokenDTO.getAccessToken()).refreshToken(tokenDTO.getRefreshToken()).user(tokenDTO.getUser()).build();
		tokenRepository.save(token);
	}

	@Override
	public Token findByAccessToken(String token) {
		return tokenRepository.findByAccessToken(token);
	}

}