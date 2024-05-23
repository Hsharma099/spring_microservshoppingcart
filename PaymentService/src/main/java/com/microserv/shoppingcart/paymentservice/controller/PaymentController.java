package com.microserv.shoppingcart.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microserv.shoppingcart.paymentservice.model.PaymentRequest;
import com.microserv.shoppingcart.paymentservice.service.PaymentService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	
	@PostMapping("/dopayment")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
		
		
		long id = paymentService.doPayment(paymentRequest);
		
		return new ResponseEntity<>(id,HttpStatus.OK);
		
	}

}
