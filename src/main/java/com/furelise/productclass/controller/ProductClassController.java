package com.furelise.productclass.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.furelise.productclass.model.ProductClass;
import com.furelise.productclass.model.ProductClassService;

@Controller
@RequestMapping("/productclass")
public class ProductClassController {

	@Autowired
	private ProductClassService pcSvc;

	@GetMapping("/all")
	public String productClassList(Model model) {
		
		model.addAttribute("productClassList", pcSvc.getAll());
		return "b-productClass-list";
	}

	@GetMapping("/add")
	public String addProductClassView(Model model) {
		
		model.addAttribute("productClass", new ProductClass());
		return "b-productClass-add";
	}
	
	@PostMapping("/add")
	public String addProductClass(@Valid ProductClass proguctClass, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "b-productClass-add";
		} else {
			boolean pass = pcSvc.addProductClass(proguctClass);
			if(pass) {
				return "/productclass/all";
			} else {
				model.addAttribute("errorMsg", "商品類別重複");
				return "b-productClass-add";
			}
		}
	}

	@PatchMapping("/update")
	public String updateProductClass(@Valid ProductClass proguctClass, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "b-productClass-update";
		} else {
			boolean isPass = pcSvc.updateProductClass(proguctClass);
			if(isPass) {
				return "productclass/all";
			} else {
				model.addAttribute("errorMsgs", "商品類別重複");
				return "b-productClass-update";
			}
		}

	}

}
