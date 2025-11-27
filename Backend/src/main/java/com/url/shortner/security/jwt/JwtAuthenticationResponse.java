package com.url.shortner.security.jwt;

public class JwtAuthenticationResponse {
 private String token;

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

@Override
public String toString() {
	return "jwtAuthenticationResponse [token=" + token + "]";
}

public JwtAuthenticationResponse(String token) {
	this.token = token;
}
 
}
