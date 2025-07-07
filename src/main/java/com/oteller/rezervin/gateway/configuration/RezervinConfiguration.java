package com.oteller.rezervin.gateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "rezervin")
@Configuration
public class RezervinConfiguration {
    private Email email;
    private Client client;
    private String tokenType;
    private String jwtSecretKey;
    private Long expirationTime;
    private Long accessTokenExpireTime;
    private Long refreshTokenExpireTime;
    
    private Storage storage = new Storage();
    
    public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public String getJwtSecretKey() {
		return jwtSecretKey;
	}

	public void setJwtSecretKey(String jwtSecretKey) {
		this.jwtSecretKey = jwtSecretKey;
	}
	
	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Long getAccessTokenExpireTime() {
		return accessTokenExpireTime;
	}

	public void setAccessTokenExpireTime(Long accessTokenExpireTime) {
		this.accessTokenExpireTime = accessTokenExpireTime;
	}

	public Long getRefreshTokenExpireTime() {
		return refreshTokenExpireTime;
	}

	public void setRefreshTokenExpireTime(Long refreshTokenExpireTime) {
		this.refreshTokenExpireTime = refreshTokenExpireTime;
	}

	public static record Email(
        String username,
        String password,
        String host,
        int port,
        String from
    ){}

    public static record Client(
        String host
    ){}
    
    public static class Storage{
    	String root = "uploads";
    	String profile = "profile";
		public String getRoot() {
			return root;
		}
		public void setRoot(String root) {
			this.root = root;
		}
		public String getProfile() {
			return profile;
		}
		public void setProfile(String profile) {
			this.profile = profile;
		}
    	
    }
}
