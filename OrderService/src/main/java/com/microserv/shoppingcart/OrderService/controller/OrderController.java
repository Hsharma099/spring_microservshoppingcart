package com.microserv.shoppingcart.OrderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microserv.shoppingcart.OrderService.model.OrderRequest;
import com.microserv.shoppingcart.OrderService.model.OrderResponse;
import com.microserv.shoppingcart.OrderService.service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	/*
	 * This method is used for placing order
	 */
	
	@PostMapping("/placeorder")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
		
		long orderId = orderService.placeOrder(orderRequest);
		log.info("OrderId: {}",orderId);
		return new ResponseEntity<>(orderId,HttpStatus.CREATED);
		
	}
	
	/*
	 * This method is used to get order details
	 */
	
	@GetMapping("/getorderdetails/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){
		
		OrderResponse orderResponse = orderService.getOrderDetails(orderId);
		
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
		
	}

}
