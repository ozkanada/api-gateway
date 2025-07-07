package com.oteller.rezervin.gateway.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;

public class SecurityContextImplHolder {
	public static Mono<Void> setContext(ServerWebExchange exchange, Authentication authentication,
			WebFilterChain chain) {
		SecurityContext context = new SecurityContextImpl(authentication);
		return chain.filter(exchange)
				.contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
	}
}
