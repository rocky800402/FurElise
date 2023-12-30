package com.furelise.ord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.furelise.ord.model.Ord;
import com.furelise.ord.model.OrdRepository;
import com.furelise.ord.model.OrdService;
import com.furelise.ord.model.OrdVO;
import com.furelise.orddetail.model.OrdDetailRepository;
import com.furelise.product.model.Product;

@Controller
@RequestMapping("/backend-ord")
public class OrdController {
	
	@Autowired
	OrdService oSvc;

	@RequestMapping(value= {"/",""}, method = RequestMethod.GET)
	public String getAllOrds(Model model) {

		model.addAttribute("ordList", oSvc.getAllOrds());
		return "b-ord-list";

	}

	@GetMapping("/detail")
	public String getOrdDetail(@RequestParam Integer ordID, Model model) {
		OrdVO ordVO = oSvc.getOrdById(ordID);
		model.addAttribute("ordVO", ordVO);
		model.addAttribute("ordDetailBOs", ordVO.getOrdDetailBOs());
		return "b-ord-detail";
	}
	
	@GetMapping("/getone")
	public String getOrdToUpdate(@RequestParam Integer ordID, Model model) {
		OrdVO ordVO = oSvc.getOrdById(ordID);
		model.addAttribute("ordVO", ordVO);
		model.addAttribute("ordDetailBOs", ordVO.getOrdDetailBOs());
		return "b-ord-update";
	}
	
	
	@PostMapping("/update")
	public String updateOrd(@RequestParam Integer ordID,Integer ordStatus, Model model) {
		Ord ord = oSvc.updateOrd(ordID,ordStatus);
        // 根据需要将更新后的订单信息放入模型中
        model.addAttribute("ord", ord);
		return "redirect:/backend-ord/";
		
	}
	
}
