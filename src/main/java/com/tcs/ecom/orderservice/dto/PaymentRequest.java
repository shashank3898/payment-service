package com.tcs.ecom.orderservice.dto;

public class PaymentRequest {
	private Long orderId;
	private Double amount;

	public PaymentRequest(Long orderId, Double amount) {
		this.orderId = orderId;
		this.amount = amount;
	}

	public PaymentRequest() {
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}