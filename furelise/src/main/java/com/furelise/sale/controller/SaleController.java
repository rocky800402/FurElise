package com.furelise.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.sale.model.Sale;
import com.furelise.sale.model.SaleRepository;

@RestController
@RequestMapping("/salecontroller")
public class SaleController {

	@Autowired
	private SaleRepository saleRepository;
	
	@CrossOrigin("*")
	@GetMapping("/saleList")
	public List<Sale> getAllSales(){
		
		List<Sale> saleList = saleRepository.findAll();
		System.out.println(saleList);
		return saleList;
		
	}
}
