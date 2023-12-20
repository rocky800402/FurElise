package com.furelise.sale.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
	@Autowired
	SaleRepository dao;
	
	//verify coupon
	public String verifyCoupon(String coupon, String total) {
		String result = "";
		//if sale in active
		List<Sale> saleList = dao.findActiveSalesWithCoupon(coupon);
		System.out.println(saleList);
		if(saleList.isEmpty())
			result = "折扣碼不存在";
		//if total >= disRequire
		else if(new BigDecimal(total).compareTo(saleList.get(0).getDisRequire()) < 0){
			result = "未達折扣門檻";
		} else 
			result = "-" + saleList.get(0).getDis();
		return result;
	}
}
