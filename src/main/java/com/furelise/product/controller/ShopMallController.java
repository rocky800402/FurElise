package com.furelise.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.furelise.product.model.Product;
import com.furelise.product.model.ProductService;
import com.furelise.productclass.model.ProductClass;
import com.furelise.productclass.model.ProductClassRepository;
import com.furelise.productclass.model.ProductClassService;
import com.furelise.shopcart.model2.ShopCartService;

@Controller
@RequestMapping("/shopmall")
public class ShopMallController {

	@Autowired
	ProductService pSvc;
	
	@Autowired
	ProductClassService pcSvc;
	
	@Autowired
	ProductClassRepository pcr;
	
	@Autowired
	ShopCartService scSvc;
	
	@GetMapping("/")
    public String showMall(Model model) {
		model.addAttribute("productList", pSvc.getAllProduct());
		  List<ProductClass> pClassList = pcr.findAll();
	        model.addAttribute("pClassList", pClassList);
        return "shopMall";  // 返回Thymeleaf模板名称
    }
	
	@GetMapping("/getone/{pID}")
	public String getOneMallProduct(@PathVariable Integer pID, Model model) {

		Product product = pSvc.getProductById(pID);
	    model.addAttribute("product", product);

	    List<ProductClass> pClassList = pcr.findAll();
	    model.addAttribute("pClassList", pClassList);

	    Product productDetail = pSvc.getProductById(pID);
	    model.addAttribute("productDetail", productDetail);
	    

	    return "mall_product_detail";
	}
	
	@GetMapping("/{pClassID}")
	public String getClassProducts(@PathVariable Integer pClassID, Model model) {
		if (pClassID != null) {
		    
		}
		model.addAttribute("productList", pSvc.getAllProduct());
		  List<ProductClass> pClassList = pcr.findAll();
	        model.addAttribute("pClassList", pClassList);
	        ProductClass productClass = pcSvc.getProductClassByID(pClassID);
//	        String pClassName = pcr.findById(pClassID).get().getPClassName();
	        model.addAttribute("productClass", productClass);
		
		return "product-by-class";
		
	}
}
