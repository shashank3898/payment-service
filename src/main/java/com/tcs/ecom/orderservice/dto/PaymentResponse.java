package com.tcs.ecom.orderservice.dto;

public class PaymentResponse {
	private boolean success;
	private String paymentId;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

}