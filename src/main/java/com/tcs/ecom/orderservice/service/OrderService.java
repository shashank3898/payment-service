package com.tcs.ecom.orderservice.service;

import com.tcs.ecom.orderservice.dto.OrderRequest;
import com.tcs.ecom.orderservice.dto.OrderStatus;
import com.tcs.ecom.orderservice.entity.Order;
import com.tcs.ecom.orderservice.exception.InvalidOrderStatusException;
import com.tcs.ecom.orderservice.exception.OrderNotFoundException;
import com.tcs.ecom.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final OrderRepository repository;

	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public Order createOrder(OrderRequest request) {
		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setAmount(request.getAmount());
		order.setStatus(OrderStatus.CREATED);
		return repository.save(order);
	}

	public Order getOrder(Long orderId) {
		return repository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
	}

	public Order updateOrderStatus(Long orderId, String status, String paymentId) {
		Order order = getOrder(orderId);
		try {
			order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
		} catch (IllegalArgumentException ex) {
			throw new InvalidOrderStatusException(status);
		}
		order.setPaymentId(paymentId);
		return repository.save(order);
	}
}
