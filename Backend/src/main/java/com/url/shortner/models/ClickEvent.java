package com.url.shortner.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
public class ClickEvent {
	@Override
	public String toString() {
		return "ClickEvent [id=" + id + ", clickDate=" + clickDate + ", urlMapping=" + urlMapping + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getClickDate() {
		return clickDate;
	}
	public void setClickDate(LocalDateTime clickDate) {
		this.clickDate = clickDate;
	}
	public UrlMapping getUrlMapping() {
		return urlMapping;
	}
	public void setUrlMapping(UrlMapping urlMapping) {
		this.urlMapping = urlMapping;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private LocalDateTime clickDate;
@ManyToOne
@JoinColumn(name="url_mapping_id")
private UrlMapping urlMapping;
}
