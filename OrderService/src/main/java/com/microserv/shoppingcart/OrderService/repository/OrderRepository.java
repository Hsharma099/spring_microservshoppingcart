package com.microserv.shoppingcart.OrderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microserv.shoppingcart.OrderService.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	

}
