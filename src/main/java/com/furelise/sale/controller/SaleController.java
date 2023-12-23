package com.furelise.sale.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furelise.sale.model.Sale;
import com.furelise.sale.model.SaleDTO;
import com.furelise.sale.model.SaleService;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleSvc;

    @GetMapping("/all")
    public String saleList(Model model) {
        model.addAttribute("saleList", saleSvc.getAll());

        return "b-sale-list";

    }

    @GetMapping("/add")
    public String addSale(Model model) {
        model.addAttribute("sale", new Sale());
        return "b-sale-add";
    }

    // 重複的coupon會停留在原畫面
    @PostMapping("/add")
    public String saleSubmit(@Valid Sale sale, BindingResult result, ModelMap model) {
        boolean dateIsBefore = sale.getSaleEnd().isBefore(sale.getSaleStart());
        if (dateIsBefore) {
            model.addAttribute("endDateError", "結束日期必須大於開始日期");
        }
        result.getAllErrors().forEach(System.out::println);
        if (result.hasErrors() || dateIsBefore) {
            return "b-sale-add";
        } else {
            boolean isPass = saleSvc.addSale(sale);
            if (isPass) {
                return "redirect:/sale/all";
            } else {
                model.addAttribute("errorMsgs", "優惠代碼重複");
                return "b-sale-add";
            }
        }
    }

    @PostMapping("/update")
    public String updateSale(@Valid @ModelAttribute Sale sale, BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		return "b-sale-update";
    	} else {
    		sale = saleSvc.updateSale(sale);
    			return "redirect:/sale/all";
    		
    	}
    }

    @PostMapping("/getone")
    public String getOne(@RequestParam Integer saleID, Model model) {
        Sale sale = saleSvc.getOneSale(saleID);
        model.addAttribute("sale", sale);
        System.out.print(sale);
        return "b-sale-update";
    }

    @PostMapping("/coupon")
    @ResponseBody
    public String verifyCoupon(@RequestBody SaleDTO req) {

        String result = saleSvc.verifyCoupon(req.getCoupon(), req.getTotal());
        return result;
    }
}