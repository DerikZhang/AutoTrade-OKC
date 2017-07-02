package com.bihang.api.bean;

import java.util.List;

public class OrdersResponse extends Response {

	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
