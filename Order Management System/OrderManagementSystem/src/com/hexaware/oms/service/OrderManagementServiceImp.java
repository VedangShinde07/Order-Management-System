package com.hexaware.oms.service;

import com.hexaware.oms.dao.IOrderManagementDao;
import com.hexaware.oms.dao.OrderManagementDaoImp;
import com.hexaware.oms.entity.User; 
import com.hexaware.oms.entity.Product; 
import com.hexaware.oms.entity.Orders;
import java.util.List;

public class OrderManagementServiceImp implements IOrderManagementService {
	private IOrderManagementDao dao = new OrderManagementDaoImp();
	
	public OrderManagementServiceImp() {
	}

	@Override
	public int createUser(User user) {
		return this.dao.createUser(user);
	}

	@Override
	public int createProduct(Product product) {
		return this.dao.createProduct(product);
	}

	@Override
	public int cancelOrder(int orderId) {
		return this.dao.cancelOrder(orderId);
	}

	@Override
	public List<Product> getAllProducts() {
		return this.dao.getAllProducts();
	}

	@Override
	public List<Orders> getOrdersByUser(int userId) {
		return this.dao.getOrdersByUser(userId);
	}

	@Override
	public Orders createOrder(User user, List<Product> products) {
		return this.dao.createOrder(user, products);
	}
}
