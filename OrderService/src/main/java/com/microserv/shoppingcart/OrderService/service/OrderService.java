package com.microserv.shoppingcart.OrderService.service;

import com.microserv.shoppingcart.OrderService.model.OrderRequest;
import com.microserv.shoppingcart.OrderService.model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long orderId);

}
