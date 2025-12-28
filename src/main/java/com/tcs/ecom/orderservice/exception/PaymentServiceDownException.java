package com.tcs.ecom.orderservice.exception;

public class PaymentServiceDownException extends RuntimeException {

	public PaymentServiceDownException(String message) {
		super(message);
	}
}