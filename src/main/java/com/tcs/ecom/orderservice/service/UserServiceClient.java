package com.tcs.ecom.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

	private final RestTemplate restTemplate;

	public UserServiceClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public boolean existsById(Long userId) {
		
		String url = "http://user-service/users/{id}/exists";
		
		try {
			return restTemplate.getForObject(url, Boolean.class, userId);
		} catch (Exception e) {
			return false;
		}
	}
}