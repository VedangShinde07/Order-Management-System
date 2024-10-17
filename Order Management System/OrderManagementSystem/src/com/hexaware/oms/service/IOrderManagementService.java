package com.hexaware.oms.service;

import com.hexaware.oms.entity.User; 
import com.hexaware.oms.entity.Product; 
import com.hexaware.oms.entity.Orders; 
import java.util.List;

public interface IOrderManagementService {

	int createUser(User user);

    int createProduct(Product product);

    int cancelOrder(int orderId);

    List<Product> getAllProducts();

    List<Orders> getOrdersByUser(int userId);
    
    Orders createOrder(User user, List<Product> products);
	
}
