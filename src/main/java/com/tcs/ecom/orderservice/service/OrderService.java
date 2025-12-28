package com.tcs.ecom.orderservice.service;

import com.tcs.ecom.orderservice.dto.*;
import com.tcs.ecom.orderservice.entity.Order;
import com.tcs.ecom.orderservice.entity.OrderItem;
import com.tcs.ecom.orderservice.exception.*;
import com.tcs.ecom.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

	private final OrderRepository repository;
	private final UserServiceClient userClient;
	private final ProductServiceClient productClient;
	private final CartServiceClient cartClient;
	private final PaymentServiceClient paymentClient;

	public OrderService(OrderRepository repository, UserServiceClient userClient, ProductServiceClient productClient,
			CartServiceClient cartClient, PaymentServiceClient paymentClient) {
		this.repository = repository;
		this.userClient = userClient;
		this.productClient = productClient;
		this.cartClient = cartClient;
		this.paymentClient = paymentClient;
	}

	@Transactional
	public Order createOrder(OrderRequest request) {
//
//	    if (!userClient.existsById(request.getUserId())) {
//	        throw new UserNotFoundException(request.getUserId());
//	    }

//	    request.getItems().forEach(item -> {
//	        if (!productClient.isAvailable(item.getProductId(), item.getQuantity())) {
//	            throw new ProductOutOfStockException(item.getProductId());
//	        }
//	    });

		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setStatus(OrderStatus.CREATED);

//	    List<OrderItem> items = request.getItems().stream().map(req -> {
//	        OrderItem item = new OrderItem();
//	        item.setProductId(req.getProductId());
//	        item.setQuantity(req.getQuantity());
//	        item.setPrice(productClient.getPrice(req.getProductId()));
//	        item.setOrder(order);
//	        return item;
//	    }).toList();

		List<OrderItem> items = request.getItems().stream().map(req -> {
			OrderItem item = new OrderItem();
			item.setProductId(101L);
			item.setQuantity(1);
			item.setPrice(1.00);
			item.setOrder(order);
			return item;
		}).toList();

		order.setItems(items);
		order.setAmount(items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());

		Order savedOrder = repository.save(order);

		// cartClient.clearCart(request.getUserId());

		paymentClient.createPayment(savedOrder.getOrderId(), savedOrder.getAmount());

		return savedOrder;
	}

	public void cancelOrder(Long orderId) {

		Order order = repository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		order.setStatus(OrderStatus.CANCELLED);
		System.out.println(order.getStatus());
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
