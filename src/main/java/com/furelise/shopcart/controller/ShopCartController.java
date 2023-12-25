package com.furelise.shopcart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.product.model.Product;
import com.furelise.product.model.ProductService;
import com.furelise.shopcart.model2.ShopCartService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@Controller
@RequestMapping("/shopcart")
public class ShopCartController {

	@Autowired
	private ShopCartService scSvc;

	@Autowired
	ProductService pSvc;

	@GetMapping("/")
	public String viewCart(@RequestParam(name = "memID", required = false) Integer memID, Model model) {

		if (memID == null) {
			String guest = "guestCart:guest";
			Map<Product, String> guestCart = scSvc.getGuestCartProducts2(guest);

			if (guestCart == null) {
				return "empty-cart";
			} else {
				
				Set<Map.Entry<Product,String>> cartEntrtSet = guestCart.entrySet();
				model.addAttribute("cartEntrtSet",cartEntrtSet);
			}
			return "shopcart";
		} else {
			String mem = "mamCart:"+ memID;
			Map<Product, String> memCart = scSvc.getGuestCartProducts2(mem);
			
			if (memCart == null) {
				return "empty-cart";
			} else {
				Set<Map.Entry<Product,String>> cartEntrtSet = memCart.entrySet();
				model.addAttribute("cartEntrtSet",cartEntrtSet);
			}

			return "shopcart";
		}
	}

	@PostMapping("/update")
	public String updateCart(@RequestParam Integer memID, @RequestParam Integer pID, @RequestParam Integer quantity) {
		scSvc.updateQuantity(memID, pID, quantity);
		return "redirect:/shopcart/view?memID=" + memID;
	}

	@PostMapping("/remove")
	public String removeFromCart(@RequestParam Integer memID, @RequestParam Integer pID) {
		scSvc.removeProduct(memID, pID);
		return "redirect:/shopcart/view?memID=" + memID;
	}

	@PostMapping("/clear")
	public String clearCart(@RequestParam Integer memID) {
		scSvc.clearCart(memID);
		return "redirect:/shopcart/view?memID=" + memID;
	}

}
