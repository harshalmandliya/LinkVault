package com.url.shortner.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.shortner.models.UrlMapping;
import com.url.shortner.models.User;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long>{
     UrlMapping findByShortUrl(String shortUrl);
     List<UrlMapping> findByUser(User user);
     
}
