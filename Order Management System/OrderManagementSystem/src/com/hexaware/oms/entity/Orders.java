package com.hexaware.oms.entity;

import java.util.List;

public class Orders {
	
	private int orderId;
	private List<Product> products;
	private int userId;
	
	public Orders() {
		super();
	}

	public Orders(int orderId, List<Product> products, int userId) {
		super();
		this.orderId = orderId;
		this.products = products;
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", products=" + products + ", userId=" + userId + "]";
	}


	

	
	
	

}
