package com.furelise.productclass.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.productclass.model.ProductClass;
import com.furelise.productclass.model.ProductClassService;

@RestController
@RequestMapping("/productClass")
public class ProductClassController {

	private ProductClassService pcSvc;

	@GetMapping("/")
	public String productClassList() {

		return "b_productClass_list";
	}

	@GetMapping("/add")
	public String addProductClass() {

		return "b_productClass_add";
	}

	@GetMapping("/update")
	public String updateProductClass(@RequestParam String pClassID, Model model) {

		ProductClass productClass = pcSvc.getProductClassByID(Integer.valueOf(pClassID));
		model.addAttribute("pClassID", pClassID);
		model.addAttribute("pClassName", productClass.getPClassName());

		return "b_productClass_update";
	}

}
