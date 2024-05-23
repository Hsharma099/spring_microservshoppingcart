package com.microserv.shoppingcart.paymentservice.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserv.shoppingcart.paymentservice.entity.PaymentTransactionDetails;
import com.microserv.shoppingcart.paymentservice.model.PaymentRequest;
import com.microserv.shoppingcart.paymentservice.repository.PaymentTransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentTransactionDetailsRepository paymentTransactionDetailsRepository;

	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("Recording payment details... {} ", paymentRequest);

		PaymentTransactionDetails transactionDetails = PaymentTransactionDetails.builder().paymentDate(Instant.now())
				                             .amount(paymentRequest.getAmount())
				                             .paymentMode(paymentRequest.getPaymentMode().name())
				                             .orderId(paymentRequest.getOrderId())
				                             .refrenceNumber(paymentRequest.getReferenceNumber())
				                             .paymentStatus("SUCCESS")
				                             .build();
		
		paymentTransactionDetailsRepository.save(transactionDetails);
		
		log.info("Transaction is completed with id: {} ",transactionDetails.getId());
		
		return transactionDetails.getId();
	}

}
