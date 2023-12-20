package com.furelise.sale.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer>{

	@Query("SELECT s FROM Sale s WHERE CURRENT_DATE BETWEEN s.saleStart AND s.saleEnd AND s.coupon = :coupon")
	List<Sale> findActiveSalesWithCoupon(@Param(value = "coupon") String coupon);
}
