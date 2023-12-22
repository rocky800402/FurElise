package com.furelise.shopcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.shopcart.model2.ShopCartService;

@Controller("/shopcart")
public class ShopCartController {

	@Autowired
	ShopCartService scSvc;

	@GetMapping("/all")
	public String ShopCart(Model model, Integer memID) {

		if (scSvc.getShopCartProducts(memID) != null) {
			model.addAttribute("shopCartList", scSvc.getShopCartProducts(memID));

			return "shopcart";
		} else {
			
			return "empty-shopcart";
		}
	}
	
	@GetMapping
	public String productRemove(Integer memID,Integer pID, Model model) {
		
		scSvc.removeProduct(memID, pID);
		model.addAttribute("shopCartList", scSvc.getShopCartProducts(memID));
		
		return "shopcart";
		
	}
	
//	//只是在頁面上修改數量
//	@GetMapping 
//	public void changeQuantity(Integer memID,Integer pID, Model model) {
//		
//	}
}
