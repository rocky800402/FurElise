package com.furelise.ecpay.payment.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.furelise.ord.model.Ord;

public interface OrdPayRepository extends JpaRepository<OrdPay, Integer>{
	List<Ord> findByMemID(Integer memID); 
	List<Ord> findByOrdStatus(Integer ordStatus);
}
