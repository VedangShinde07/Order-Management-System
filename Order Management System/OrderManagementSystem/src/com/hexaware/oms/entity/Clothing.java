package com.hexaware.oms.entity;

public class Clothing {
	
	private int productId;
	private String size;
    private String color;
    
	public Clothing() {
		super();
	}

	public Clothing(int productId, String size, String color) {
		super();
		this.productId = productId;
		this.size = size;
		this.color = color;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Clothing [size=" + size + ", color=" + color + "]";
	}



	
	
	

    
	
}
