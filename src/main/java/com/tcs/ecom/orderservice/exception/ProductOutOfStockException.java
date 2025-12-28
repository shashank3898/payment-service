package com.tcs.ecom.orderservice.exception;

public class ProductOutOfStockException extends RuntimeException {
	
	public ProductOutOfStockException(Long id) {
		super("Product out of stock: " + id);
	}
}