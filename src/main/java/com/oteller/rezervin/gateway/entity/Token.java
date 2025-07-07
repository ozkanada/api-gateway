package com.oteller.rezervin.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="access_token")
	private String accessToken;
	
	@Column(name="refresh_token")
	private String refreshToken;
    
	@Transient
    private String prefix = "Bearer";
    
    @JsonIgnore
    @ManyToOne
    private User user;
    
    public Token(String accessToken, String refreshToken, User user) {
    	this.accessToken = accessToken;
    	this.refreshToken = refreshToken;
    	this.user = user;
    }
 
}
