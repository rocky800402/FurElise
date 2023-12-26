package com.furelise.shopcart.controller;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.mem.model.entity.Mem;
import com.furelise.ord.model.OrdService;
import com.furelise.product.model.Product;
import com.furelise.product.model.ProductService;
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

	@GetMapping("/")
	public String viewCart(@RequestParam(name = "memID", required = false) Integer memID, Model model) {

		if (memID == null) {
			String guest = "guestCart:guest";
			Map<Product, String> guestCart = scSvc.getCartProducts(guest);

			if (guestCart == null) {
				return "empty-cart";
			} else {

				Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
				model.addAttribute("cartEntrtSet", cartEntrtSet);
			}

		} else {

			String cartID = memID.toString();

			Map<Product, String> memCart = scSvc.getCartProducts(cartID);

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
	public String removeFromCart(@RequestParam(name = "memID", required = false) Integer memID,
			@RequestParam Integer pID, Model model) {
		if (memID == null) {
			String guest = "guestCart:guest";
			Map<Product, String> guestCart = scSvc.getCartProducts(guest);
			scSvc.removeProduct(memID, pID); 

			Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
			model.addAttribute("cartEntrtSet", cartEntrtSet);
		}else {
			String cartID = "memCart:" + memID;
			Map<Product, String> guestCart = scSvc.getCartProducts(cartID);
			scSvc.removeProduct(memID, pID); 

			Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
			model.addAttribute("cartEntrtSet", cartEntrtSet);
			
		}
		return "redirect:/shopcart/?memID="+memID;

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
	public String updateCart(@RequestParam Integer memID, @RequestParam Integer pID, @RequestParam Integer quantity) {
		scSvc.updateQuantity(memID, pID, quantity);
		return "redirect:/shopcart/";
	}

//	@PostMapping("/clear")
//	public String clearCart(@RequestParam Integer memID) {
//		scSvc.clearCart(memID);
//		return "redirect:/shopcart/view?memID=" + memID;
//	}
	
	//將memID存在session
//	  @GetMapping("/")
//	    public String viewCart(HttpServletRequest req, Model model) {
//	        HttpSession session = req.getSession();
//	        Integer memID = (Integer) session.getAttribute("memID");
//
//	        if (memID == null) {
//	            String guest = "guestCart:guest";
//	            Map<Product, String> guestCart = scSvc.getCartProducts(guest);
//
//	            if (guestCart == null) {
//	                return "empty-cart";
//	            } else {
//	                Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
//	                model.addAttribute("cartEntrtSet", cartEntrtSet);
//	            }
//	        } else {
//	            String cartID = memID.toString();
//	            Map<Product, String> memCart = scSvc.getCartProducts(cartID);
//
//	            if (memCart == null || memCart.isEmpty()) {
//	                return "empty-cart";
//	            } else {
//	                Set<Map.Entry<Product, String>> cartEntrtSet = memCart.entrySet();
//	                model.addAttribute("cartEntrtSet", cartEntrtSet);
//	            }
//	        }
//
//	        return "shopcart";
//	    }
//
//	    @PostMapping("/remove")
//	    public String removeFromCart(HttpServletRequest req, @RequestParam Integer pID, Model model) {
//	        HttpSession session = req.getSession();
//	        Integer memID = (Integer) session.getAttribute("memID");
//
//	        if (memID == null) {
//	            String guest = "guestCart:guest";
//	            Map<Product, String> guestCart = scSvc.getCartProducts(guest);
//	            scSvc.removeProduct(memID, pID);
//
//	            Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
//	            model.addAttribute("cartEntrtSet", cartEntrtSet);
//	        } else {
//	            String cartID = "memCart:" + memID;
//	            Map<Product, String> guestCart = scSvc.getCartProducts(cartID);
//	            scSvc.removeProduct(memID, pID);
//
//	            Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
//	            model.addAttribute("cartEntrtSet", cartEntrtSet);
//	        }
//	        return "redirect:/shopcart/?memID=" + memID;
//	    }
//
//	    @PostMapping("/checkout")
//	    public String checkout(HttpServletRequest req) {
//	        HttpSession session = req.getSession();
//	        Mem mem = (Mem) session.getAttribute("mem");
//
//	        if (mem == null) {
//	            return "redirect:/login";
//	        }
//	        return "redirect:/shopcart-checkout";
//	    }

}
