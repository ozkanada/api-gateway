package com.oteller.rezervin.gateway.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.oteller.rezervin.gateway.service.impl.UserDetailsServiceImpl;

import org.springframework.security.core.Authentication;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements WebFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthenticationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    	/*if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
    	    exchange.getResponse().setStatusCode(HttpStatus.OK);
    	    return exchange.getResponse().setComplete();
    	}*/

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            String username;
            try {
                username = jwtUtil.extractUsername(token);
            } catch (Exception e) {
            	exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return userDetailsService.findByUsername(username)
            		.flatMap(userDetails -> {
            	        if (jwtUtil.validateToken(token, userDetails)) {
            	            Authentication auth = new UsernamePasswordAuthenticationToken(
            	                    userDetails, null, userDetails.getAuthorities());
            	            // SecurityContext ve sonra chain.filter dönüyor: Mono<Void>
            	            return SecurityContextImplHolder.setContext(exchange, auth, chain);
            	        } else {
            	            // token geçersizse doğrudan next filter
            	        	exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            	        	return exchange.getResponse().setComplete();
            	        }
            		})
            		.switchIfEmpty(Mono.defer(() -> {
                        // Kullanıcı bulunamazsa 401
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }));
               
        }

        return chain.filter(exchange);
    }
}

