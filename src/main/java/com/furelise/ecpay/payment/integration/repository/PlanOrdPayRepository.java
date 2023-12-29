package com.furelise.ecpay.payment.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.furelise.planord.model.*;

@Repository
public interface PlanOrdPayRepository extends JpaRepository<PlanOrdPay, Integer>{
	List<PlanOrdPay> findByMemID(Integer memID);
	
	PlanOrdPay findByMemIDAndPlanStatusID(Integer memID, Integer planStatusID);
	
	//planOrdService
	@Query("SELECT p FROM PlanOrd p WHERE p.memID = :memID AND p.planEnd > CURRENT_DATE + 3")
	List<PlanOrdPay> findLaterPlanEnd(@Param(value = "memID") Integer memID);
	
	//pickupWayService
	List<PlanOrdPay> findByWayID(Integer wayID);
	
	//planService
	List<PlanOrdPay> findByPlanID(Integer planID);
	
	//pickupTimeService
	List<PlanOrdPay> findByTimeID(Integer timeID);
}
