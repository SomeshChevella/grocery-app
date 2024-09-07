package com.grocery.rest.webservices.grocery_app.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllProducts() {
		Product product1 = new Product(1, "Carrot", "Vegetable", "Fresh Veggies LLC", "lbs", 2.59);
		Product product2 = new Product(2, "Apple", "Fruit", "Fresh Veggies LLC", "lbs", 3.59);

		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

		List<Product> products = productController.getAllProducts();
		assertThat(products).hasSize(2);
		assertThat(products).contains(product1, product2);
	}

	@Test
	public void testGetProductsById_Success() {
		Product product1 = new Product(1, "Carrot", "Vegetable", "Fresh Veggies LLC", "lbs", 2.59);

		when(productRepository.findById(1)).thenReturn(Optional.of(product1));

		Product foundProduct = productController.getProductsById(1);
		assertThat(foundProduct).isEqualTo(product1);
	}
	
	@Test
	public void testCreateProduct() throws JsonProcessingException, Exception {
		Product product = new Product(3, "Apple", "Fruit", "Fresh Veggies LLC", "lbs", 3.59);
		when(productRepository.save(any(Product.class))).thenReturn(product);
		
		mockMvc.perform(post("/products")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(product)))
	            .andExpect(status().isCreated())
	            .andExpect(header().string("Location", "/products/" + product.getProduct_id()));
    }
}
