package com.oteller.rezervin.gateway.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import com.oteller.rezervin.gateway.entity.User;
import java.util.Collection;

public class AppUserDetails implements UserDetails {

	private static final long serialVersionUID = 6658855933294987172L;
	private Long id;
    private String username;
    private String password;
    //private Collection<SimpleGrantedAuthority> authorities;
    private boolean enabled;

    /*public AppUserDetails(String username, String password, Collection<SimpleGrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }*/
    
    public AppUserDetails(User user) {
    	 this.id = user.getUserId();
         this.username = user.getUsername();
         this.password = user.getPassword();
         this.enabled = user.isActive();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return enabled; }
}
