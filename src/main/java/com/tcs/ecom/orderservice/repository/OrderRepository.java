package com.tcs.ecom.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.ecom.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
