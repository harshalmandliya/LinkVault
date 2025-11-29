package com.url.shortner.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.url.shortner.dtos.UrlMappingDTO;
import com.url.shortner.models.UrlMapping;
import com.url.shortner.models.User;
import com.url.shortner.repository.UrlMappingRepository;

@Service
public class UrlMappingService {
       
	private UrlMappingRepository urlMappingRepository;
	public UrlMappingService(UrlMappingRepository urlMappingRepository) {
		this.urlMappingRepository = urlMappingRepository;
	}

	public UrlMappingDTO createShortUrl(String originalUrl, User user) {
		String shortUrl=generateShortUrl();
		UrlMapping urlMapping=new UrlMapping();
		urlMapping.setOriginalUrl(originalUrl);
		urlMapping.setShortUrl(shortUrl);
		urlMapping.setUser(user);
		urlMapping.setCreatedDate(LocalDateTime.now());
		UrlMapping savedUrlMapping=urlMappingRepository.save(urlMapping);
		return converToDto(savedUrlMapping);
	}
	
	private UrlMappingDTO converToDto(UrlMapping urlMapping) {
		UrlMappingDTO urlMappingDTO=new UrlMappingDTO();
		urlMappingDTO.setId(urlMapping.getId());
		urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
		urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
		urlMappingDTO.setClickCount(urlMapping.getClickCount());
		urlMappingDTO.setCreatedDate(urlMapping.getCreatedDate());
		urlMappingDTO.setUsername(urlMapping.getUser().getUsername());
		return urlMappingDTO;
	}

	private String generateShortUrl() {
		String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random=new Random();
		StringBuilder shortUrl=new StringBuilder(8);
		for(int i=0;i<8;i++) {
			shortUrl.append(characters.charAt(random.nextInt(characters.length())));
		}
		return shortUrl.toString();
	}
	

}
