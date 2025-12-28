package com.tcs.ecom.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.ecom.orderservice.dto.PaymentRequest;
import com.tcs.ecom.orderservice.exception.PaymentServiceDownException;

@Service
public class PaymentServiceClient {

	private final RestTemplate restTemplate;

	public PaymentServiceClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void createPayment(Long orderId, Double amount) {

		String url = "http://payment-service/payments/create";

		PaymentRequest request = new PaymentRequest();
		request.setOrderId(orderId);
		request.setAmount(amount);

		try {
			restTemplate.postForObject(url, request, Object.class);
		} catch (Exception ex) {
			throw new PaymentServiceDownException("Payment service unavailable");
		}
	}
}
