package com.furelise.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.product.model.Product;
import com.furelise.product.model.ProductService;
import com.furelise.productclass.model.ProductClass;
import com.furelise.productclass.model.ProductClassService;

@Controller
@RequestMapping("/product-backen")
public class ProductBackenController {
	
	@Autowired
	ProductService pSvc;
	
	@Autowired
	ProductClassService pcSvc;
	
	@GetMapping("/b-product-list")
    public String showProductList() {
        return "b-product-list";  // 返回Thymeleaf模板名称
    }
	
	@GetMapping("/all")
	public String  productList(Model model) {
		model.addAttribute("productList", pSvc.getAllProduct());
		return "b-product-list";
	}
	
	@GetMapping("/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "b-product-add";
	}
	
	@PostMapping("/add")
	public String productSubmit(@Valid Product product, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "b-product-add";
		} else {
			boolean isPass = pSvc.addProduct(product);
			if(isPass) {
				return "redirect:/product-backen/all";
			} else {
				model.addAttribute("errorMsgs", "商品名稱重複");
				return "b-product-add";
			}
		}
	}
	
	@PostMapping("/getone")
	public String getOne(@RequestParam Integer pID, Model model) {
		Product product = pSvc.getProductById(pID);
		model.addAttribute("product", product);
		return "b-product-update";
	}
	
	@GetMapping("/check-detail")
	public String checkDetail(@RequestParam Integer pID, Model model) {
		Product product = pSvc.getProductById(pID);
		model.addAttribute("product", product);
		return "b-product-detail";
	}
	
	@ModelAttribute("productClassListData")
	protected List<ProductClass> referenceListData() {
		List<ProductClass> list = pcSvc.getAll();
		return list;
	}

}
