package com.furelise.shopcart.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furelise.mem.model.entity.Mem;
import com.furelise.ord.model.OrdService;
import com.furelise.product.model.Product;
import com.furelise.product.model.ProductService;
import com.furelise.sale.model.SaleDTO;
import com.furelise.sale.model.SaleService;
import com.furelise.shopcart.model2.ShopCartService;

@Controller
@RequestMapping("/shopcart")
public class ShopCartController {

	@Autowired
	private ShopCartService scSvc;

	@Autowired
	ProductService pSvc;

	@Autowired
	private SaleService saleSvc;

	@Autowired
	private OrdService oSvc;

	@RequestMapping(value= {"/",""}, method = RequestMethod.GET)
	public String viewCart(@RequestParam(name = "memID", required = false) Integer memID, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Mem mem = (Mem) session.getAttribute("mem");
		System.out.println(mem);
		if (Objects.isNull(mem)) {
			String guestCartkey = (String) session.getAttribute("guestCart");
			System.out.println("guestCartkey");
			System.out.println(guestCartkey);
			if (Objects.isNull(guestCartkey)) {
				return "empty-cart";
			} 
			Map<Product, String> guestCart = scSvc.getCartProducts(guestCartkey);
			System.out.println("guestCart");
			System.out.println(guestCart);
			if (guestCart.isEmpty()) {
				return "empty-cart";
			} else {
				Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
				System.out.println("cartEntrtSet");
				System.out.println(cartEntrtSet);
				model.addAttribute("cartEntrtSet", cartEntrtSet);
			}
		} else {
			String key = "memCart:" + mem.getMemID();
			Map<Product, String> memCart = scSvc.getCartProducts(key);
			if (memCart == null || memCart.isEmpty()) {
				return "empty-cart";
			} else {
				Set<Map.Entry<Product, String>> cartEntrtSet = memCart.entrySet();
				model.addAttribute("cartEntrtSet", cartEntrtSet);
			}
		}

		return "shopcart";

	}

	@PostMapping("/remove")
	public String removeFromCart(@RequestParam Integer pID, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Mem mem = (Mem) session.getAttribute("mem");
		if (Objects.isNull(mem)) {
			String guestCartkey = (String) session.getAttribute("guestCart");
			Map<Product, String> guestCart = scSvc.getCartProducts(guestCartkey);
			scSvc.removeProduct(guestCartkey, pID);
			Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
			if(cartEntrtSet.isEmpty()) session.removeAttribute("guestCart");
			model.addAttribute("cartEntrtSet", cartEntrtSet);
		} else {
			String key = "memCart:" + mem.getMemID();
			Map<Product, String> guestCart = scSvc.getCartProducts(key);
			scSvc.removeProduct(key, pID);
			Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
			model.addAttribute("cartEntrtSet", cartEntrtSet);
		}
		return "redirect:/shopcart";

	}

	@PostMapping("/checkout")
	public String checkout(HttpServletRequest req) {
		Mem mem = (Mem) req.getSession().getAttribute("mem");
		if (mem == null) {
			return "redirect:/login";
		}
		return "redirect:/shopcart-checkout";

	}

	@PostMapping("/update")
	public String updateCart(@RequestParam Integer pID, @RequestParam Integer quantity, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Mem mem = (Mem) session.getAttribute("mem");
		String key = Objects.isNull(mem)? (String) session.getAttribute("guestCart"): "memCart:" + mem.getMemID();
		scSvc.updateQuantity(key, pID, quantity);
		return "redirect:/shopcart";
	}

//	@PostMapping("/clear")
//	public String clearCart(@RequestParam Integer memID) {
//		scSvc.clearCart(memID);
//		return "redirect:/shopcart/view?memID=" + memID;
//	}

}
