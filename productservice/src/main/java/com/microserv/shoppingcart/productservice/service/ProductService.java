package com.microserv.shoppingcart.productservice.service;

import java.util.List;

import com.microserv.shoppingcart.productservice.model.ProductRequest;
import com.microserv.shoppingcart.productservice.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	List<ProductResponse> getAllProducts();

	void reduceQuantity(long productId, long quantity);

}
