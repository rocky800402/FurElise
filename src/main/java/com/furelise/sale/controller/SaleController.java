package com.furelise.sale.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.sale.model.Sale;
import com.furelise.sale.model.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	private SaleService saleSvc;

	@GetMapping("/all")
	public String saleList(Model model) {
		model.addAttribute("saleList", saleSvc.getAll());

		return "b_sale_list";

	}

	@GetMapping("/add")
	public String addSale(Model model) {
		
		model.addAttribute("sale", new Sale());
		
		return "b_sale_add";
	}
	
	// 重複的coupon會停留在原畫面
	@PostMapping("/add")
	public String saleSubmit(@Valid Sale sale, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "b_sale_add";
		}
		System.out.println("coupon= " + sale.getCoupon());
		return saleSvc.addSale(sale);
	}

	@PostMapping("/update")
	public String updateSale(@Valid @ModelAttribute Sale sale, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "b_sale_update";
		}
		saleSvc.updateSale(sale);
		return "all";

	}
	
	@PostMapping("/getone")
	public String getOne(@RequestParam Integer saleID, Model model) {
		Sale sale = saleSvc.getOneSale(saleID);
		model.addAttribute("sale", sale);
		System.out.print(sale);
		return "b_sale_update";
	}
}