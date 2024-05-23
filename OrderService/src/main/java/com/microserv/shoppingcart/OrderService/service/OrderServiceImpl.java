package com.microserv.shoppingcart.OrderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserv.shoppingcart.OrderService.entities.Order;
import com.microserv.shoppingcart.OrderService.exception.CustomException;
import com.microserv.shoppingcart.OrderService.external.client.PaymentService;
import com.microserv.shoppingcart.OrderService.external.client.ProductService;
import com.microserv.shoppingcart.OrderService.external.request.PaymentRequest;
import com.microserv.shoppingcart.OrderService.model.OrderRequest;
import com.microserv.shoppingcart.OrderService.model.OrderResponse;
import com.microserv.shoppingcart.OrderService.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;

	@Override
	public long placeOrder(OrderRequest orderRequest) {
		// orderentity-> save the data with status order created
		// call product service-> block products(Reduce quantity)
		// call payment service--> for payments-> if order successful--completed
		// else ---cancelled

		log.info("Placing orer request: {}", orderRequest);
		//Calling product service to reduce quantity
		productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

		Order order = Order.builder().
				amount(orderRequest.getTotalamount())
				.orderStatus("CREATED")
				.orderDate(Instant.now())
				.productId(orderRequest.getProductId())
				.quantity(orderRequest.getQuantity())
				.build();
		
		 order = orderRepository.save(order);
		 
		 log.info("calling payment service to complete payment");
		 PaymentRequest paymentRequest = PaymentRequest.builder()
				 									 .amount(orderRequest.getTotalamount())
				                                     .orderId(order.getId())
				                                     .paymentMode(orderRequest.getPaymentMode())
				                                     .build();
		 String orderStatus = null; 
		 try {
			paymentService.doPayment(paymentRequest);
			log.info("payment done successfully. Chnaging payment status to placed");
			orderStatus = "PLACED";
		} catch (Exception e) {
			log.error("Payment failed.Changing order status to failed");
			orderStatus = "PAYMENT_FAILED";
		}
		//now need to set order status again as it is changed on the basis of payment.		 	       
		 order.setOrderStatus(orderStatus);
		 orderRepository.save(order);
		 log.info("Order placed successfully with orderId: {} ",order.getId());

		return order.getId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) {
		
		log.info("Getting order details for orderId: {} ",orderId);
		
		
		Order order = orderRepository.findById(orderId).orElseThrow(
									 ()-> 
									 new CustomException("Order not found for orderId: "+orderId,
											 "NOT_FOUND",404));
		
		OrderResponse orderResponse = OrderResponse.builder()
													.amount(order.getAmount())
													.orderDate(order.getOrderDate())
													.orderId(order.getId())
													.orderStatus(order.getOrderStatus())
													.build();
		
		
		return orderResponse;
	}

}
