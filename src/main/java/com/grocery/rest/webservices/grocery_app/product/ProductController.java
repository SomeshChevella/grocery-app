package com.grocery.rest.webservices.grocery_app.product;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	private ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Product getProductsById(@PathVariable int id) {
		Optional<Product> product= productRepository.findById(id);
		if(product.get()==null)
			throw new ProductNotFoundException("id "+ id);
		
		return product.get();
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {		
		Product savedProduct=productRepository.save(product);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(product.getProduct_id())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProductById(@PathVariable int id) {
		productRepository.deleteById(id);
	}
	
	@PutMapping("/products/{id}")
	public Product patchProduct(@PathVariable int id, @RequestBody Product product) {
		if(!productRepository.existsById(id)) {
			throw new ProductNotFoundException("id "+id);
		}
		product.setProduct_id(id);
		
		return productRepository.save(product);
	}
	
}
