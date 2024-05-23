package com.microserv.shoppingcart.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserv.shoppingcart.productservice.entities.Product;
import com.microserv.shoppingcart.productservice.exception.ProductServiceCustomException;
import com.microserv.shoppingcart.productservice.model.ProductRequest;
import com.microserv.shoppingcart.productservice.model.ProductResponse;
import com.microserv.shoppingcart.productservice.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {

		log.info("Adding product...");

		Product product = Product.builder().product_Name(productRequest.getProductName())
				.price(productRequest.getPrice()).quantity(productRequest.getQuantity()).build();

		productRepository.save(product);

		log.info("Product created....");

		return product.getProduct_Id();
	}

	@Override
	public ProductResponse getProductById(long productId) {

		log.info("Get the product for productId: {} " + productId);

		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ProductServiceCustomException("Product with the given id not found", "PRODUCT_NOT_FOUND"));

		ProductResponse productResponse = ProductResponse.builder().ProductName(product.getProduct_Name())
				.price(product.getPrice()).productID(product.getProduct_Id()).quantity(product.getQuantity()).build();

		/*
		 * 2nd option
		 */
//		ProductResponse productResponse = new ProductResponse();
//		BeanUtils.copyProperties(product, productResponse);

		return productResponse;
	}

	@Override
	public List<ProductResponse> getAllProducts() {

		log.info("Getting all products..... ");

		List<Product> allProducts = productRepository.findAll();

		List<ProductResponse> productResponses = allProducts.stream().map(allProduct -> {
			return ProductResponse.builder().ProductName(allProduct.getProduct_Name()).price(allProduct.getPrice())
					.productID(allProduct.getProduct_Id()).quantity(allProduct.getQuantity()).build();
		}).collect(Collectors.toList());

		/*
		 * 2nd option
		 */

//		List<ProductResponse> productResponses = allProducts.stream().map(allProduct->{
//			ProductResponse productResponse = new ProductResponse();
//			BeanUtils.copyProperties(allProduct, productResponse);
//			return productResponse;
//			}).collect(Collectors.toList());

		return productResponses;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {

		log.info("Reduce quantity {} for id: {}", quantity, productId);

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductServiceCustomException("Product with id not found", "PRODUCT_NOT_FOUND"));

		if (product.getQuantity() < quantity) {
			throw new ProductServiceCustomException("product doe not have sufficient quantity",
					"INSUFFICIENT_QUANTITY");
		}
		product.setQuantity(product.getQuantity() - quantity);
		productRepository.save(product);
		log.info("Product quantity updated successfully...");
	}

}
