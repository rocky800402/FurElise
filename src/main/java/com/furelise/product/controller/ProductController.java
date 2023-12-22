package com.furelise.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.furelise.product.model.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService pSvc;
	
	@GetMapping("/shopmall")
	@RequestBody
	public String ShopMall(Model model) {
	
		return "shopMall";
	}
}
