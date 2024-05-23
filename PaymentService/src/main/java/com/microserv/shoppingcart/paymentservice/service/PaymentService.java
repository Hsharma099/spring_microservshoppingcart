package com.microserv.shoppingcart.paymentservice.service;

import com.microserv.shoppingcart.paymentservice.model.PaymentRequest;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

}
