package com.oteller.rezervin.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private final AuthenticationFilter authenticationFilter;

    public SecurityConfiguration(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }
    
    
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults()) // CORS aktif ama tanımı dışarıdan geliyor
                .authorizeExchange(exchange -> exchange
                		.pathMatchers(HttpMethod.OPTIONS).permitAll() 
                        .pathMatchers("/api/1.0/auth/**").permitAll()
                        .anyExchange().authenticated()
                )
                .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
    
    /*@Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // CORS aktif ama tanımı dışarıdan geliyor
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/1.0/auth/**").permitAll()
                        .anyExchange().authenticated()
                )
                .build();
    }*/

    /*@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf(csrf -> csrf.disable())
            .headers(headers -> headers.disable())
            .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(new AuthEntryPoint()))
            .authorizeHttpRequests((authorization) ->
                authorization
                    .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.PUT, "/api/1.0/user/{id}")).authenticated()
                    .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, "/api/1.0/user/{id}")).authenticated()
                    .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/1.0/user/{id}")).authenticated()
                    .anyRequest().permitAll()
            );

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }*/
    
    /*@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        	.cors(cors -> {})
            .headers(headers -> headers.disable())
            .authorizeHttpRequests((authorization) ->authorization
            		.requestMatchers("/api/1.0/auth/**").permitAll()
            		.requestMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyRequest().authenticated()
            );

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }*/
    
    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
    	return new WebMvcConfigurer() {
    		public void addCorsMappings(CorsRegistry registry) {
    			registry.addMapping("/**")
    					.allowedOrigins("*").allowedMethods("*");
    		}
    	};
    }*/

    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3011")); 
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true); // Eğer cookie veya token gönderiliyorsa

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/
    
    

}
