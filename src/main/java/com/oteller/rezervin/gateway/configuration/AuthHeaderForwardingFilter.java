package com.oteller.rezervin.gateway.configuration;

import java.util.stream.Collectors;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthHeaderForwardingFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return ReactiveSecurityContextHolder.getContext().flatMap(securityContext -> {
			Authentication auth = securityContext.getAuthentication();
			if (auth != null && auth.isAuthenticated()) {
				AppUserDetails principal = (AppUserDetails) auth.getPrincipal();
				ServerHttpRequest request = exchange.getRequest().mutate()
						.header("X-User-Id", principal.getId().toString())
						.header("X-Username", principal.getUsername())
						.header("X-Roles",
								principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
										.map(role -> role.replace("ROLE_", "")).collect(Collectors.joining(",")))
						.build();
				return chain.filter(exchange.mutate().request(request).build());
			} else {
				return chain.filter(exchange); // No auth, pass through
			}
		});
	}

}
