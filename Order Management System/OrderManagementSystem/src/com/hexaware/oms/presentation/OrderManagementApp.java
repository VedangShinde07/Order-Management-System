package com.hexaware.oms.presentation;

import com.hexaware.oms.entity.User; 
import com.hexaware.oms.entity.Product; 
import com.hexaware.oms.entity.Orders; 
import com.hexaware.oms.service.IOrderManagementService;
import com.hexaware.oms.service.OrderManagementServiceImp;


import java.util.List;
import java.util.Scanner;

public class OrderManagementApp {
	
	 static Scanner scanner;

	    static {
	        scanner = new Scanner(System.in);
	    }

	    public OrderManagementApp() {
	    }

	public static void main(String[] args) {
		
		boolean flag = true;
        IOrderManagementService service = new OrderManagementServiceImp();

        while(true) {
        	while(flag) {
        		 System.out.println("***** Welcome to Order Management System *****");
                 System.out.println("1. CREATE USER");
                 System.out.println("2. CREATE PRODUCT");
                 System.out.println("3. CANCEL ORDER");
                 System.out.println("4. SHOW ALL PRODUCTS");
                 System.out.println("5. GET ORDERS BY USER");
                 System.out.println("6. CREATE ORDER");
                 System.out.println("0. EXIT / LOGOUT");
                 int choice = scanner.nextInt();
                 switch(choice) {
                 case 0: 
                	 flag = false;
                     System.out.println("Thank you, logout successfully..");
                     break;
                 case 1:
                	 User user = readUserData(); // Method to read user data
                	 int userCount = service.createUser(user);
                	 if (userCount > 0) {
                	     System.out.println("User created successfully..");
                	 } else {
                	     System.err.println("User creation failed...");
                	 }
                	 break;
                 case 2:
                	 Product newProduct = readProductData(); // Method to read product data
                     int productCreationResult = service.createProduct(newProduct);
                     if (productCreationResult > 0) {
                         System.out.println("Product created successfully.");
                     } else {
                         System.err.println("Product creation failed.");
                     }
                     break;
                 case 3:
                	 System.out.print("Enter Order ID to cancel: ");
                     int orderIdToCancel = scanner.nextInt();
                     int cancelResult = service.cancelOrder(orderIdToCancel);
                     if (cancelResult > 0) {
                         System.out.println("Order canceled successfully.");
                     } else {
                         System.err.println("Order cancellation failed.");
                     }
                     break;
                 case 4: 
                	 List<Product> allProducts = service.getAllProducts();
                     System.out.println("Available Products:");
                     for (Product product : allProducts) {
                         System.out.println(product);
                     }
                     break;
                     case 5:
                    	 System.out.print("Enter User ID to fetch orders: ");
                    	 int userId = scanner.nextInt();

                    	 // Fetch the orders for the provided userId
                    	 List<Orders> userOrders = service.getOrdersByUser(userId);

                    	 System.out.println("Orders for User ID " + userId + ":");
                    	 for (Orders order : userOrders) {
                    	     // Assuming the Orders class has a proper toString() method to print order details
                    	     System.out.println("Order ID: " + order.getOrderId());
                    	     System.out.println("Products: " + order.getProducts());
                    	     System.out.println("User ID: " + order.getUserId());
                    	 }
                    	 break;
                         
                     default:
                         System.err.println("Invalid choice, please try again.");
                         break;
                	 
                 }
        	}
        }
        
	}
	public static User readUserData() {
	    User user = new User();
	    
	    System.out.println("Enter User ID:");
	    user.setUserId(scanner.nextInt());  // Assuming User class has a setUserId method
	    
	    System.out.println("Enter User Name:");
	    user.setUserName(scanner.next());    // Assuming User class has a setUserName method
	    
	    System.out.println("Enter Password:");
	    user.setPassword(scanner.next());     // Assuming User class has a setPassword method
	    
	    System.out.println("Enter Role:");
	    user.setRole(scanner.next());         // Assuming User class has a setRole method
	    
	    return user;
	}
	
	public static Product readProductData() {
	    Product product = new Product();
	    
	    System.out.println("Enter Product ID:");
	    product.setProductId(scanner.nextInt());  // Assuming Product class has a setProductId method
	    
	    System.out.println("Enter Product Name:");
	    product.setProductName(scanner.next());    // Assuming Product class has a setProductName method
	    
	    System.out.println("Enter Description:");
	    product.setDescription(scanner.next());     // Assuming Product class has a setDescription method
	    
	    System.out.println("Enter Price:");
	    product.setPrice(scanner.nextDouble());      // Assuming Product class has a setPrice method
	    
	    System.out.println("Enter Quantity in Stock:");
	    product.setQuantityInStock(scanner.nextInt()); // Assuming Product class has a setQuantityInStock method
	    
	    System.out.println("Enter Product Type:");
	    product.setType(scanner.next());              // Assuming Product class has a setType method
	    
	    return product;
	}
	
	public static Product readProductsForOrder() {
	    Product product = new Product();

	    System.out.println("Enter Product ID:");
	    product.setProductId(scanner.nextInt());  // Assuming Product class has a setProductId method

	    System.out.println("Enter Product Name:");
	    product.setProductName(scanner.next());    // Assuming Product class has a setProductName method

	    System.out.println("Enter Product Description:");
	    product.setDescription(scanner.next());     // Assuming Product class has a setDescription method

	    System.out.println("Enter Price:");
	    product.setPrice(scanner.nextDouble());      // Assuming Product class has a setPrice method

	    System.out.println("Enter Quantity in Stock:");
	    product.setQuantityInStock(scanner.nextInt()); // Assuming Product class has a setQuantityInStock method

	    System.out.println("Enter Product Type:");
	    product.setType(scanner.next());             // Assuming Product class has a setType method

	    return product;
	}
	
}
        
	    
	    
	    
	    