package com.ptit.model;

public class Product {
	private long id; 
	private String name; 
	private String picture; 
	private String describe; 
	private float price;
	
	public Product() {
		
	}
	public Product(long id, String name, String picture, String describe, float price) {
		this.id = id; 
		this.name = name; 
		this.picture = picture; 
		this.describe = describe; 
		this.price = price; 
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	} 
	
}
