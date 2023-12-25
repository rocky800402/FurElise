package com.furelise.shopcart.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.shopcart.model2.ShopCartService;

@Controller
@RequestMapping("/shopcart")
public class ShopCartController {

    @Autowired
    private ShopCartService scSvc;
    
    @GetMapping("/")
    public String viewCart(@RequestParam(name = "memID", required = false) Integer memID, Model model) {
        if (memID == null) {
        	String guest = "guestCart:guest";
            // 如果 memID 為 null，將其視為訪客，執行相應的邏輯
            model.addAttribute("cart", scSvc.getGuestCartProducts(guest));
            Map<String, String> guestCart = scSvc.getGuestCartProducts(guest);
            if(guestCart == null) {
            	return "empty-cart";
            }
            return "shopcart";
        } else {
            // 如果 memID 不為 null，正常處理
            model.addAttribute("cart", scSvc.getShopCartProducts(memID));
            Map<String, String> memCart = scSvc.getShopCartProducts(memID);
            if(memCart == null) {
            	return "empty-cart";
            }
        }
        return "shopcart";
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

//	//只是在頁面上修改數量
//	@GetMapping 
//	public void changeQuantity(Integer memID,Integer pID, Model model) {
//		
//	}
}
