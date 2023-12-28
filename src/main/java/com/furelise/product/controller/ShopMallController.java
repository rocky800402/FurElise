package com.furelise.product.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.mem.model.entity.Mem;
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

	@RequestMapping(value= {"/",""}, method = RequestMethod.GET)
	public String showMall(Model model) {
		model.addAttribute("productList", pSvc.getAllProduct());
		List<ProductClass> pClassList = pcr.findAll();
		model.addAttribute("pClassList", pClassList);
		return "m-shopMall"; // 返回Thymeleaf模板名称
	}

	@GetMapping("/getone/{pID}")
	public String getOneMallProduct(@PathVariable Integer pID, Model model) {

		Product product = pSvc.getProductById(pID);
		model.addAttribute("product", product);

		List<ProductClass> pClassList = pcr.findAll();
		model.addAttribute("pClassList", pClassList);

		Product productDetail = pSvc.getProductById(pID);
		model.addAttribute("productDetail", productDetail);

		return "m-product_detail";
	}

	@GetMapping("/{pClassID}")
	public String getClassProducts(@PathVariable Integer pClassID, Model model) {
		if (pClassID != null) {

		}
		model.addAttribute("productList", pSvc.getAllProduct());
		List<ProductClass> pClassList = pcr.findAll();
		model.addAttribute("pClassList", pClassList);
		ProductClass productClass = pcSvc.getProductClassByID(pClassID);
		model.addAttribute("productClass", productClass);

		return "m-product-by-class";

	}

	@PostMapping("/add/{pID}")
	public String addToCart(@PathVariable Integer pID, @RequestParam Integer quantity, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Mem mem = (Mem) session.getAttribute("mem");
		String key = (String) session.getAttribute("guestCart");
		if (Objects.isNull(key) && Objects.isNull(mem)) {
			UUID uuid = UUID.randomUUID();
			key = "guestCart:guest" + uuid;
			session.setAttribute("guestCart", key);
		} else if (!Objects.isNull(mem)) {
			key = "memCart:" + mem.getMemID();
		}
		scSvc.addProduct(key, pID, quantity);
		return "redirect:/shopmall";
	}
}
