package com.grocery.rest.webservices.grocery_app.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity(name="product")
public class Product {
	@Id
	@GeneratedValue
	private int product_id;
	private String name;
	private String category;
	@Size(min = 5,max = 25)
	private String company;
	private String measure;
	private double price;
	
	public Product(){}
	
	public Product(int product_id, String name, String category, String company, String measure, double price) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.category = category;
		this.company = company;
		this.measure=measure;
		this.price = price;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", name=" + name + ", category=" + category + ", company="
				+ company + ", measure= "+ measure + ", price=" + price + "]";
	}
}
