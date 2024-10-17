package com.hexaware.oms.dao;

import com.hexaware.oms.entity.User;
import com.hexaware.oms.entity.Product; 
import com.hexaware.oms.entity.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderManagementDaoImp implements IOrderManagementDao {
	
	private Connection conn = DBUtil.getDBConnection();
	
	public OrderManagementDaoImp() {
    }

	@Override
	public int createUser(User user) {
		int count = 0;
	    String insert = "INSERT INTO User(userId, username, password, role) VALUES (?, ?, ?, ?)";

	    try {
	        PreparedStatement pstmt = this.conn.prepareStatement(insert);
	        pstmt.setInt(1, user.getUserId()); 
	        pstmt.setString(2, user.getUserName()); 
	        pstmt.setString(3, user.getPassword()); 
	        pstmt.setString(4, user.getRole()); 
	        count = pstmt.executeUpdate();
	    } catch (SQLException var5) {
	        SQLException e = var5;
	        e.printStackTrace();
	    }

	    return count;
	}

	@Override
	public int createProduct(Product product) {
		int count = 0;
	    String insert = "INSERT INTO Product(productId, productName, description, price, quantityInStock, type) VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        PreparedStatement pstmt = this.conn.prepareStatement(insert);
	        pstmt.setInt(1, product.getProductId());
	        pstmt.setString(2, product.getProductName()); 
	        pstmt.setString(3, product.getDescription()); 
	        pstmt.setDouble(4, product.getPrice()); 
	        pstmt.setInt(5, product.getQuantityInStock()); 
	        pstmt.setString(6, product.getType()); 
	        count = pstmt.executeUpdate();
	    } catch (SQLException var5) {
	        SQLException e = var5;
	        e.printStackTrace();
	    }

	    return count;
	}

	@Override
	public int cancelOrder(int orderId) {
		int count = 0;
	    String delete = "DELETE FROM Orders WHERE orderId = ?";

	    try {
	        PreparedStatement pstmt = this.conn.prepareStatement(delete);
	        pstmt.setInt(1, orderId); 
	        count = pstmt.executeUpdate();
	    } catch (SQLException var5) {
	        SQLException e = var5;
	        e.printStackTrace();
	    }

	    return count; 
	}

	@Override
	public List<Product> getAllProducts() {
		 List<Product> list = new ArrayList<>();
		    String selectAll = "SELECT * FROM Product"; 

		    try {
		        PreparedStatement pstmt = this.conn.prepareStatement(selectAll);
		        ResultSet rs = pstmt.executeQuery();

		        while (rs.next()) {
		            int productId = rs.getInt("productId");
		            String productName = rs.getString("productName");
		            String description = rs.getString("description");
		            double price = rs.getDouble("price");
		            int quantityInStock = rs.getInt("quantityInStock");
		            String type = rs.getString("type");
		            Product product = new Product(productId, productName, description, price, quantityInStock, type);
		            list.add(product);
		        }
		    } catch (SQLException var11) {
		        SQLException e = var11;
		        e.printStackTrace();
		    }

		    return list; 
	}
	
	@Override
	public List<Orders> getOrdersByUser(int userId) {
		 List<Orders> orderList = new ArrayList<>();
		    String selectOrders = "SELECT * FROM Orders WHERE userId = ?";

		    try {
		        PreparedStatement pstmt = this.conn.prepareStatement(selectOrders);
		        pstmt.setInt(1, userId); // Pass userId directly as an int
		        ResultSet rs = pstmt.executeQuery();

		        while (rs.next()) {
		            int orderId = rs.getInt("orderId");
		            // Assuming you have a method to retrieve products by orderId
		            List<Product> products = getProductsByOrderId(orderId); // Use your method to retrieve products
		            Orders order = new Orders();
		            order.setOrderId(orderId);
		            order.setProducts(products);
		            order.setUserId(userId); // Set the userId for the order
		            orderList.add(order);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return orderList;
	}



	

	public List<Product> getProductsByOrderId(int orderId) {
	    List<Product> productList = new ArrayList<>();
	    String selectProducts = "SELECT p.productId, p.productName, p.description, p.price, p.quantityInStock, p.type " +
	                            "FROM Products p " +
	                            "JOIN OrderProducts op ON p.productId = op.productId " +  // Assuming you have a junction table for orders and products
	                            "WHERE op.orderId = ?";

	    try {
	        PreparedStatement pstmt = this.conn.prepareStatement(selectProducts);
	        pstmt.setInt(1, orderId);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int productId = rs.getInt("productId");
	            String productName = rs.getString("productName");
	            String description = rs.getString("description");
	            double price = rs.getDouble("price");
	            int quantityInStock = rs.getInt("quantityInStock");
	            String type = rs.getString("type");

	            // Create a Product object and add it to the list
	            Product product = new Product(productId, productName, description, price, quantityInStock, type);
	            productList.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return productList;
	}

	@Override
	public Orders createOrder(User user, List<Product> products) {
		int count = 0;
	    Orders newOrder = null;

	    // Prepare the SQL for inserting the order
	    String insertOrder = "INSERT INTO Orders(userId) VALUES (?)";

	    try {
	        PreparedStatement pstmt = this.conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
	        pstmt.setInt(1, user.getUserId());
	        count = pstmt.executeUpdate();

	        
	        if (count > 0) {
	            // Retrieve the generated orderId
	            ResultSet generatedKeys = pstmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int orderId = generatedKeys.getInt(1); // Get the generated orderId
	                newOrder = new Orders(); // Create a new Orders object
	                newOrder.setOrderId(orderId); // Set the orderId
	                newOrder.setUserId(user.getUserId()); // Set the userId
	                newOrder.setProducts(products); // Set the products

	                // Insert products associated with the order
	                String insertOrderProducts = "INSERT INTO OrderProducts(orderId, productId) VALUES (?, ?)";
	                for (Product product : products) {
	                    PreparedStatement pstmtProduct = this.conn.prepareStatement(insertOrderProducts);
	                    pstmtProduct.setInt(1, orderId);
	                    pstmtProduct.setInt(2, product.getProductId());
	                    pstmtProduct.executeUpdate(); // Insert each product associated with the order
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return newOrder;
	}

	
	

}
