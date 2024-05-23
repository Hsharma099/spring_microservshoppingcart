package com.microserv.shoppingcart.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microserv.shoppingcart.paymentservice.entity.PaymentTransactionDetails;

@Repository
public interface PaymentTransactionDetailsRepository extends JpaRepository<PaymentTransactionDetails, Long> {

}
