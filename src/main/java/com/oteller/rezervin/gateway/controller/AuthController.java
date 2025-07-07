package com.oteller.rezervin.gateway.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oteller.rezervin.gateway.controller.request.AuthRequest;
import com.oteller.rezervin.gateway.controller.request.UserRegisterRequest;
import com.oteller.rezervin.gateway.controller.response.AuthResponse;
import com.oteller.rezervin.gateway.controller.response.FriendlyMessage;
import com.oteller.rezervin.gateway.exception.enums.FriendlyMessageCodes;
import com.oteller.rezervin.gateway.exception.utils.FriendlyMessageUtils;
import com.oteller.rezervin.gateway.service.IAuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(value = "/api/1.0/auth")
@CrossOrigin
public class AuthController {

    private final IAuthService authService;
    
	
	public AuthController(IAuthService authService) {
		this.authService = authService;
	}
    
	@PostMapping(value = "/register")
	public Mono<ResponseEntity<AuthResponse>> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
		log.debug("[{}][createUser] -> request: {}", this.getClass().getSimpleName(), userRegisterRequest);
		return authService.registerUser(userRegisterRequest)
				.map(authResponse -> {
					log.debug("[{}][createUser] -> response: {}", this.getClass().getSimpleName(), authResponse);
					var cookie = ResponseCookie.from("pandora-token", authResponse.getAccessToken())
							.path("/")
							.httpOnly(true)
							.build();
					return ResponseEntity.ok()
							.header(HttpHeaders.SET_COOKIE, cookie.toString())
							.body(authResponse);
				});
	}


	@PostMapping("/login")
	public Mono<ResponseEntity<AuthResponse>> handleAuthentication(@Valid @RequestBody AuthRequest authRequest) {
	    return authService.authenticate(authRequest)
	        .map(authResponse -> {
	            ResponseCookie cookie = ResponseCookie
	                .from("pandora-token", authResponse.getAccessToken())
	                .path("/")
	                .httpOnly(true)
	                .build();

	            return ResponseEntity.ok()
	                .header(HttpHeaders.SET_COOKIE, cookie.toString())
	                .body(authResponse);
	        });
	}

    
    @PostMapping("/logout")
    ResponseEntity<FriendlyMessage> handleLogout(@RequestHeader(name="Authorization", required = false) String authorizationHeader, @CookieValue(name="pandora-token", required = false) String cookieValue){
    	var tokenWithPrefix = authorizationHeader;
        if(cookieValue != null){
            tokenWithPrefix = "AnyPrefix " +cookieValue;
        }
        authService.logout(tokenWithPrefix);
        var cookie = ResponseCookie.from("pandora-token", "").path("/").maxAge(0).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(FriendlyMessage.builder()
                .title(FriendlyMessageUtils.getFriendlyMessage(FriendlyMessageCodes.SUCCESS))
                .description(FriendlyMessageUtils.getFriendlyMessage(FriendlyMessageCodes.LOGOUT_SUCCESSFULY)).build());
    }
}
