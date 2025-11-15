package com.url.shortner.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.url.shortner.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;


public class UserDetailsImpl implements UserDetails{
public UserDetailsImpl() {
		
	}
private static final long serialVersionUID=1L;
private Long id;
@Override
public String toString() {
	return "UserDetailsImpl [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
			+ ", authorities=" + authorities + "]";
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public void setUsername(String username) {
	this.username = username;
}

public void setPassword(String password) {
	this.password = password;
}

public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
	this.authorities = authorities;
}
private String username;
private String email;
private String password;
private Collection <? extends GrantedAuthority> authorities;


	public UserDetailsImpl(Long id, String username, String email, String password,
		Collection<? extends GrantedAuthority> authorities) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password = password;
	this.authorities = authorities;
}

	public static UserDetailsImpl build(User user) {
		GrantedAuthority authority= new SimpleGrantedAuthority(user.getRole());
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), Collections.singletonList(authority));
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
