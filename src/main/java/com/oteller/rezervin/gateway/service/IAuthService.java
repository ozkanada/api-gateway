package com.oteller.rezervin.gateway.service;

import com.oteller.rezervin.gateway.controller.request.AuthRequest;
import com.oteller.rezervin.gateway.controller.request.UserRegisterRequest;
import com.oteller.rezervin.gateway.controller.response.AuthResponse;

import reactor.core.publisher.Mono;

public interface IAuthService {
    Mono<AuthResponse> authenticate(AuthRequest authRequest);
    
    void logout(String authorizationHeader);
    
    Mono<AuthResponse> registerUser(UserRegisterRequest userRegisterRequest);
}
