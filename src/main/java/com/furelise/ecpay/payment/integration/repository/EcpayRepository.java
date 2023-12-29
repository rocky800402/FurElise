package com.furelise.ecpay.payment.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcpayRepository extends JpaRepository<PlanOrdPay,Integer>{

}
