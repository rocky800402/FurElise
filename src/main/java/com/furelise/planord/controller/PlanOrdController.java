package com.furelise.planord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.planord.model.*;
import com.furelise.plan.model.*;

@Controller
@RequestMapping("/planord")
public class PlanOrdController {
	
	
	@Autowired
	PlanOrdService planOrdSvc;
	@Autowired
	PlanService planSvc;
	
	@GetMapping("/")
	public String planOrdList() {
		return "planord_list";
	}
	
//	查看詳情
	@GetMapping("/detail")
	public String planDetail(@RequestParam String planOrdID, Model model) {
		PlanOrd planOrd = planOrdSvc.getPlanOrdById(Integer.valueOf(planOrdID));
		Plan plan = planSvc.getPlanById(planOrd.getPlanID());
		
		model.addAttribute("planOrdID", planOrdID);
		model.addAttribute("memName", planOrdSvc.getNameById(planOrd.getMemID()));
		model.addAttribute("planName", plan.getPlanName());
		model.addAttribute("timeRange", planOrdSvc.getTimeRange(planOrd.getTimeID()));
		model.addAttribute("day", planOrdSvc.getWeekDay(planOrd.getDay()));
		model.addAttribute("wayName", planOrdSvc.getWayName(planOrd.getWayID()));
		model.addAttribute("total", planOrd.getTotal());
		model.addAttribute("planPeriod", planOrdSvc.getPlanPeriod(planOrd.getPeriodID()));
		model.addAttribute("planStart", planOrd.getPlanStart());
		model.addAttribute("planEnd", planOrd.getPlanEnd());
		model.addAttribute("cityCode", planOrd.getCityCode());
		model.addAttribute("floor", planOrd.getFloor());
		model.addAttribute("pickupStop", planOrd.getPickupStop());
		model.addAttribute("contact", planOrd.getContact());
		model.addAttribute("contactTel", planOrd.getContactTel());
		model.addAttribute("planOrdDate", planOrd.getPlanOrdDate());
		model.addAttribute("amendLog", planOrd.getAmendLog());
		return "planord_detail";
	}
	
	@GetMapping("/intro")
	public String planIntro() {
		return "planord_intro";
	}
	
	@GetMapping("/shop")
	public String planShop() {
		return "planord_shop";
	}
	
	
	
	//	//暫不做修改功能
//	@GetMapping("/update")
//	public String updatePlanOrd(@RequestParam String planOrdID, Model model) {
//		PlanOrd planOrd = planOrdSvc.getPlanOrdById(Integer.valueOf(planOrdID));
//		model.addAttribute("planOrdID", planOrd.getPlanOrdID());
//		model.addAttribute("planID", planOrd.getPlanID());
//		return "planord_update";
//	}
}
