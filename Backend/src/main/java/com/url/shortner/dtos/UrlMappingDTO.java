package com.url.shortner.dtos;

import java.time.LocalDateTime;

public class UrlMappingDTO {
       private Long id;
       private String originalUrl;
       private String shortUrl;
       private int clickCount;
       private LocalDateTime createdDate;
       private String  username;
       
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "UrlMappingDTO [id=" + id + ", originalUrl=" + originalUrl + ", shortUrl=" + shortUrl + ", clickCount="
				+ clickCount + ", createdDate=" + createdDate + ", username=" + username + "]";
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
