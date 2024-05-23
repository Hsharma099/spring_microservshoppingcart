package com.microserv.shoppingcart.OrderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microserv.shoppingcart.OrderService.external.request.PaymentRequest;


@FeignClient(name = "PaymentService/payment")
public interface PaymentService {
	
	@PostMapping("/dopayment")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

}
