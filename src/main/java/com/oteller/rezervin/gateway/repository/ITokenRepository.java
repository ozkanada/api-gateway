package com.oteller.rezervin.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oteller.rezervin.gateway.entity.Token;

public interface ITokenRepository extends JpaRepository<Token, Long>{
	
	Token findByAccessToken(String token);

}
