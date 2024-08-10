package com.grocery.rest.webservices.grocery_app.product;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private static List<Product> productList=new ArrayList<>();
	private static int count=0;
	static {
		productList.add(new Product(++count,"Carrot","Vegetable","Fresh Veggies LLC","lbs",2.59));
		productList.add(new Product(++count,"Apple","Fruit","Fresh Veggies LLC","lbs",3.59));
		productList.add(new Product(++count,"Cucumber","Vegetable","Greens LLC","unit",0.99));
		productList.add(new Product(++count,"Almond Milk","Diary","Holy Cows LLC","unit",5.59));
	}
	public List<Product> getAllProducts() {
		return productList;
	}
	public Product getProductById(int id) {
		return productList.stream()
				.filter(product->product.getProduct_id()==id)
				.findFirst()
				.orElseThrow(()->new ProductNotFoundException("id: "+id ));
	}
	public Product createProduct(Product product) {
		product.setProduct_id(++count);
		productList.add(product);
		return product;
	}
	public void deleteProductById(int id) {
		productList.removeIf(product->product.getProduct_id()==id);
	}
}
