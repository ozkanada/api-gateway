package com.oteller.rezervin.gateway.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
	
	private String accessToken;
	private String refreshToken;

}
