package com.furelise.sale.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer>{
boolean existsByCoupon(String coupon);
	
	@Query("SELECT saleID FROM Sale WHERE coupon = :coupon")
	Sale findSaleByCoupon(@Param(value = "coupon")String coupon);
	
	@Query("SELECT disRequire FROM Sale WHERE coupon = :coupon")
	BigDecimal findDisRequireByCoupon(@Param(value = "coupon")String coupon);
	
	@Query("SELECT dis FROM Sale WHERE coupon = :coupon")
	BigDecimal findDisByCoupon(@Param(value = "coupon")String coupon);
	
	@Query("SELECT saleStart FROM Sale WHERE coupon = :coupon")
	BigDecimal findSaleStartByCoupon(@Param(value = "coupon")String coupon);
	
	@Query("SELECT saleStart FROM Sale WHERE coupon = :coupon")
	BigDecimal findSaleEndByCoupon(@Param(value = "coupon")String coupon);
	
	@Query("SELECT s FROM Sale s WHERE CURRENT_DATE BETWEEN s.saleStart AND s.saleEnd AND s.coupon = :coupon")
	List<Sale> findActiveSalesWithCoupon(@Param(value = "coupon") String coupon);
}
