package com.oteller.rezervin.gateway.service;

import com.oteller.rezervin.gateway.controller.response.dto.TokenDTO;
import com.oteller.rezervin.gateway.entity.Token;

public interface ITokenService {
	void saveToken(TokenDTO tokenDTO);

	Token findByAccessToken(String token);
}
