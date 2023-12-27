package com.furelise.ord.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.mem.model.entity.Mem;
import com.furelise.ord.model.MemOrdService;
import com.furelise.ord.model.OrdService;

@RestController
@RequestMapping("/backen-ord")
public class OrdController {
	
	@Autowired
	OrdService oSvc;
	
	@Autowired
	MemOrdService moSvc;
	
	@GetMapping("/all")
	public String getAllOrds(Model model) {
		model.addAttribute("ordList", oSvc);

        return "b-ord-list";

	}
}
