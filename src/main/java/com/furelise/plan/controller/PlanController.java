package com.furelise.plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.plan.model.*;

@Controller
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
	PlanService planSvc;
	
	// return view
	@GetMapping("/")
	public String planList() {
		return "plan_list";
	}
	
	// return view
	@GetMapping("/add")
	public String addPlan() {
		return "plan_create";
	}
	
	// return view
	@GetMapping("/update")
	public String updatePlan(@RequestParam String planID, Model model) {
		Plan plan = planSvc.getPlanById(Integer.valueOf(planID));
		model.addAttribute("planID", planID);
		model.addAttribute("planName", plan.getPlanName() );
		model.addAttribute("liter", plan.getLiter());
		model.addAttribute("planPrice", plan.getPlanPrice());
		model.addAttribute("planPricePerCase", plan.getPlanPricePerCase());
		model.addAttribute("times", plan.getTimes());
		return "plan_update";
	}
	
}
