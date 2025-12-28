package com.tcs.ecom.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OrderRequest {

	@NotNull
	private Long userId;

	@NotNull
	private List<OrderItemRequest> items;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<OrderItemRequest> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}

}
