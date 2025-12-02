package com.url.shortner.service;
import  org.springframework.security.core.Authentication;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.url.shortner.dtos.LoginRequest;
import com.url.shortner.dtos.UrlMappingDTO;
import com.url.shortner.models.User;
import com.url.shortner.repository.UrlMappingRepository;
import com.url.shortner.repository.UserRepository;
import com.url.shortner.security.jwt.JwtAuthenticationResponse;
import com.url.shortner.security.jwt.JwtUtils;

@Service
public class UserService {

    private final UrlMappingRepository urlMappingRepository;

    private  PasswordEncoder passwordEncoder;
    private  UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public UserService(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils
    , UrlMappingRepository urlMappingRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.urlMappingRepository = urlMappingRepository;
    }


    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication=authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        		);
        		SecurityContextHolder.getContext().setAuthentication(authentication);
        		UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();
        		String jwt=jwtUtils.generateToken(userDetails);
    	return new JwtAuthenticationResponse(jwt);
    }
    
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


	public User findByUsername(String name) {
		return userRepository.findByUsername(name).orElseThrow(()->new UsernameNotFoundException("User not found with username" + name));
	}


}
