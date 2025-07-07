package com.oteller.rezervin.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
@EnableDiscoveryClient
//@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@EntityScan(basePackages = "com.eywatech.pandora.entity")
public class ApiGatawayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatawayApplication.class, args);
	}
	
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
	
	/*@Bean
	CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		//config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedOrigins(List.of("http://localhost:3011"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		config.setAllowedMethods(Arrays.stream(HttpMethod.values()).map(HttpMethod::name).collect(Collectors.toList()));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}*/

}
