package com.url.shortner.security.jwt;

import java.awt.RenderingHints.Key;
import java.util.Date;
import java.util.Base64.Decoder;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.url.shortner.service.UserDetailsImpl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtUtils {
	@Value("${jwt.secret}")
private String jwtSecret;
	@Value("${jwt.expiration}")
private int jwtExpirationMs;
	
	public String getJwtFormHeader(HttpServletRequest request) {
		String bearerToken=request.getHeader("Authorization");
		if(bearerToken!=null&&bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
	
	public String generateToken(UserDetailsImpl userDetails){ 
		String username=userDetails.getUsername();
		String roles=userDetails.getAuthorities()
				.stream()
				.map(authority->authority.getAuthority())
				.collect(Collectors.joining(","));
		return Jwts.builder() 
				.subject(username) 
				.claim("roles", roles) 
				.issuedAt(new Date())
				.expiration(new Date((new Date().getTime()+jwtExpirationMs))) 
				.signWith(key()) 
				.compact();
	}
	
	public String getUserNameWithJwtToken(String token) {
		return Jwts
				.parser()
				.verifyWith(key())
				.build().parseSignedClaims(token)
				.getPayload().getSubject();
	}
	
	private SecretKey key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public boolean validateToken(String authToken) {
	try {	Jwts.parser().verifyWith(key())
		.build().parseSignedClaims(authToken);
		return true;}
	catch(JwtException e) {
		throw new RuntimeException(e);
	}
	catch(Exception e) {
		throw new RuntimeException(e);
	}
	
	}
}
