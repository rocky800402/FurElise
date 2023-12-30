package com.furelise.shopcart.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.city.model.CityService;
import com.furelise.mem.model.entity.Mem;
import com.furelise.ord.model.OrdDTO;
import com.furelise.ord.model.OrdService;
import com.furelise.product.model.Product;
import com.furelise.product.model.ProductService;
import com.furelise.shopcart.model2.ShopCartService;

@Controller
@RequestMapping("/shopcart")
public class ShopCartController {

	@Autowired
	private ShopCartService scSvc;

	@Autowired
	ProductService pSvc;

	@Autowired
	OrdService oSvc;

	@Autowired
	CityService cSvc;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String viewCart(@RequestParam(name = "memID", required = false) Integer memID, Model model,
			HttpServletRequest req, OrdDTO ordDTO) {
		HttpSession session = req.getSession();
		Mem mem = (Mem) session.getAttribute("mem");
		if (Objects.isNull(mem)) { // 沒有登入
			String guestCartkey = (String) session.getAttribute("guestCart");
			if (Objects.isNull(guestCartkey)) { // 也沒有建立過訪客購物車
				return "empty-cart";
			}
			Map<Product, String> guestCart = scSvc.getCartProducts(guestCartkey);
			if (guestCart.isEmpty()) { // 有建立過訪客購物車但現在裡面沒有商品
				return "empty-cart";
			} else { // 有建立過訪客購物車且裡面有商品
				Set<Map.Entry<Product, String>> cartEntrtSet = guestCart.entrySet();
				model.addAttribute("cartEntrtSet", cartEntrtSet);
				model.addAttribute("cityList", cSvc.getAllCity());
			}
		} else { // 已登入的會員
			String key = "memCart:" + mem.getMemID();
			Map<Product, String> memCart = scSvc.getCartProducts(key);

			if (memCart == null || memCart.isEmpty()) { // 會員購物車為空
				String guestCartKey = (String) session.getAttribute("guestCart"); // 取得之前的訪客購物車
				
				if (guestCartKey != null && !guestCartKey.isEmpty()) { // 訪客購物車不為空
					Map<Product, String> guestCart = scSvc.getCartProducts(guestCartKey);

					if (!guestCart.isEmpty()) { // 訪客購物車內有商品
						// 建立新的會員購物車
						memCart = new HashMap<>(guestCart);
						// 將會員購物車存回資料庫
						memCart = scSvc.saveCartProducts(key, memCart); // key是會員購物車id,memCart是從訪客購物車取得的商品
						// 刪除原來的訪客購物車
			            scSvc.clearCart(guestCartKey);
						// 將購物車內容加到 model
						Set<Map.Entry<Product, String>> cartEntrtSet2 = memCart.entrySet();
						model.addAttribute("cartEntrtSet", cartEntrtSet2);
						model.addAttribute("cityList", cSvc.getAllCity());
						model.addAttribute("ordDTO", ordDTO);

						return "shopcart";
					}
				}

				// 若訪客購物車為空或者訪客購物車內沒有商品，回傳 "empty-cart"
				return "empty-cart";

			} else { // 購物車內有商品
				Set<Map.Entry<Product, String>> cartEntrtSet = memCart.entrySet();
				model.addAttribute("cartEntrtSet", cartEntrtSet);
				model.addAttribute("cityList", cSvc.getAllCity());
				model.addAttribute("ordDTO", ordDTO);
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
			if (cartEntrtSet.isEmpty())
				session.removeAttribute("guestCart");
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
	public String checkout(HttpServletRequest req, Model model,
			@RequestParam(name = "checkoutBtn", required = false) String checkoutBtn, OrdDTO ordDTO) {
		Mem mem = (Mem) req.getSession().getAttribute("mem");
		if (mem == null) {
			return "redirect:/login";
		} else {

			if ("submitCheckout".equals(checkoutBtn)) {
				oSvc.createOrder(ordDTO, model, req);
				// 重定向到 "/mem-ord"
				return "redirect:/memOrdDetail.html";
			}
			return "redirect:/memOrdDetail.html";
		}
	}

	@PostMapping("/update")
	public String updateCart(@RequestParam Integer pID, @RequestParam Integer quantity, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Mem mem = (Mem) session.getAttribute("mem");
		String key = Objects.isNull(mem) ? (String) session.getAttribute("guestCart") : "memCart:" + mem.getMemID();
		scSvc.updateQuantity(key, pID, quantity);
		return "redirect:/shopcart";
	}
	
	@GetMapping("/empty-cart")
	public String showEmptyCartPage() {
	    return "empty-cart";
	}
//	@PostMapping("/clear")
//	public String clearCart(@RequestParam Integer memID) {
//		scSvc.clearCart(memID);
//		return "redirect:/shopcart/view?memID=" + memID;
//	}

}
