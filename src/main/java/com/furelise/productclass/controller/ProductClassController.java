package com.furelise.productclass.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String addProductClass(@Valid ProductClass productClass, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "b-productClass-add";
		} else {
			boolean pass = pcSvc.addProductClass(productClass);
			if(pass) {
				return "redirect:/productclass/all";
			} else {
				model.addAttribute("errorMsg", "商品類別重複");
				return "b-productClass-add";
			}
		}
	}
	
	@PostMapping("/getone")
	public String getOne(@RequestParam Integer pClassID, Model model) {
		ProductClass productClass = pcSvc.getProductClassByID(pClassID);
		model.addAttribute("productClass", productClass);
		
		return "b-productClass-update";
	}

	@PostMapping("/update")
	public String updateProductClass(@Valid ProductClass proguctClass, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "b-productClass-update";
		} else {
			boolean isPass = pcSvc.updateProductClass(proguctClass);
			if(isPass) {
				return "redirect:/productclass/all";
			} else {
				model.addAttribute("errorMsgs","商品類別名稱重複");
				return "b-productClass-update";
			}
		
		}
	}
		//		if (result.hasErrors()) {
//			return "b-productClass-update";
//		}
//		pcSvc.updateProductClass(proguctClass);
//		return "b-productClass-list";
//
//	}
		


} //ProductClassController
