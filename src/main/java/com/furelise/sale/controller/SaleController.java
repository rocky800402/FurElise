package com.furelise.sale.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.sale.model.*;

@RestController
@RequestMapping("/salecontroller")
public class SaleController {

	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	SaleService saleSvc;
	
	
	@GetMapping("/saleList")
	public List<Sale> getAllSales(){
		
		List<Sale> saleList = saleRepository.findAll();
		System.out.println(saleList);
		return saleList;
		
	}
	
	@PostMapping("/coupon")
	public String verifyCoupon(@RequestBody SaleDTO req) {
		
		String result = saleSvc.verifyCoupon(req.getCoupon(), req.getTotal());
		return result;
	}
}
