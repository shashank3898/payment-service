package com.tcs.ecom.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceClient {

	private final RestTemplate restTemplate;

	public CartServiceClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void clearCart(Long userId) {
		
		String url = "http://cart-service/cart/{userId}/clear";
		
		try {
			restTemplate.postForObject(url, null, Void.class, userId);
		} catch (Exception e) {
			throw new RuntimeException("Failed to clear cart for user " + userId);
		}
	}
}