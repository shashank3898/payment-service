package com.tcs.ecom.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceClient {

	private final RestTemplate restTemplate;

	public ProductServiceClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public boolean isAvailable(Long productId, int quantity) {
		
		String url = "http://product-service/products/{id}/availability?quantity={qty}";
		
		try {
			return restTemplate.getForObject(url, Boolean.class, productId, quantity);
		} catch (Exception e) {
			return false;
		}
	}

	public double getPrice(Long productId) {
		
		String url = "http://product-service/products/{id}/price";
		
		try {
			return restTemplate.getForObject(url, Double.class, productId);
		} catch (Exception e) {
			throw new RuntimeException("Cannot fetch price for product " + productId);
		}
	}
}