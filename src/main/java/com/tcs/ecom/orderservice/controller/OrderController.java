package com.tcs.ecom.orderservice.controller;

import com.tcs.ecom.orderservice.dto.OrderRequest;
import com.tcs.ecom.orderservice.entity.Order;
import com.tcs.ecom.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService service;

	public OrderController(OrderService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable Long id) {
		return ResponseEntity.ok(service.getOrder(id));
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestParam String status,
			@RequestParam(required = false) String paymentId) {

		return ResponseEntity.ok(service.updateOrderStatus(id, status, paymentId));
	}
}
