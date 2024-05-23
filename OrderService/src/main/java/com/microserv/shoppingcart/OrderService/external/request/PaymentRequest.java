package com.microserv.shoppingcart.OrderService.external.request;

import com.microserv.shoppingcart.OrderService.model.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
	
	private long orderId;
	private long amount;
	private String referenceNumber;
	private PaymentMode paymentMode;

}
