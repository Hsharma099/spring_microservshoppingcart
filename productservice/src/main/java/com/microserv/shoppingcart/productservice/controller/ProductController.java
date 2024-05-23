package com.microserv.shoppingcart.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microserv.shoppingcart.productservice.model.ProductRequest;
import com.microserv.shoppingcart.productservice.model.ProductResponse;
import com.microserv.shoppingcart.productservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/addproduct")
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
		
		//This method will return product_id as output
		long productId = productService.addProduct(productRequest);
		return new ResponseEntity<>(productId,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
		
		ProductResponse productResponse = productService.getProductById(productId);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/getallproducts")
	public ResponseEntity<List<ProductResponse>> getAllProducts(){
		
		List<ProductResponse> allProducts = productService.getAllProducts();
		
		return new ResponseEntity<>(allProducts,HttpStatus.OK);
	}
	
	@PutMapping("/reducequantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId,
			@RequestParam long quantity){
		
		productService.reduceQuantity(productId,quantity);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
