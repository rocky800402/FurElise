package com.furelise.period.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.period.model.*;

@Controller
@RequestMapping("/period")
public class PeriodController {

	@Autowired
	PeriodService periodSvc;
	
	// return view
	@RequestMapping(value= {"/",""}, method = RequestMethod.GET)
	public String periodList() {
		return "period_list";
	}

	// return view
	@GetMapping("/add")
	public String addperiod() {
		return "period_create";
	}
	
	// return view
	@GetMapping("/update") 
	public String updateperiod(@RequestParam String periodID, Model model) {
		Period period = periodSvc.getPeriodById(Integer.valueOf(periodID));
		model.addAttribute("periodID", periodID);
		model.addAttribute("planPeriod", period.getPlanPeriod());
		return "period_update";
	}

}
