package com.furelise.plan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.plan.model.*;

@RestController
@RequestMapping("/plan")
public class PlanRESTCon {

	@Autowired
	PlanService planSvc;
	
	//一次產生五筆
	@PostMapping("/adding")
	public String addPlan(@Valid @RequestBody Plan req) {
		return planSvc.addPlan(req); 
	}
	
	
	@PutMapping("/updating")
	public String updatePlan(@Valid @RequestBody Plan req) {
		return planSvc.updatePlan(req);
	}
	
	//同名方案一次刪除
	@DeleteMapping("deleting")
	public String deletePlan(@RequestBody Plan req) {
		return planSvc.deletePlan(req.getPlanName());
	}

	
	@GetMapping("/all")
	public List<Plan> getAllPlans(){
		List<Plan> planList = planSvc.getAllPlan();
		return planList;
	}
	
//	//一次一筆，ajax和service內容都要改寫才能用
//	
//	@PostMapping("/adding")
//	public Plan addPlan(@RequestBody Plan req) {
//		return planSvc.addPlan(req);
//	}
	
//	
//	@GetMapping("/{planID}")
//	public Plan getPlanById(@PathVariable Integer planID) {
//		return planSvc.getPlanById(planID);
//	}
}
