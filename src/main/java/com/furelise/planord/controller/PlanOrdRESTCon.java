package com.furelise.planord.controller;

import java.util.List;
import java.util.Set;

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

import com.furelise.mem.model.*;
import com.furelise.planord.model.*;
import com.furelise.planstatus.model.*;

@RestController
@RequestMapping("/planord")
public class PlanOrdRESTCon {
	
	@Autowired
	PlanOrdService planOrdSvc;

	Mem mem = new Mem();

	// add data, for ajax using
	@CrossOrigin("*")
	@PostMapping()
	public PlanOrd addPlanOrd(@RequestBody PlanOrd req) {
//	驗證使用者是否登入
//	取得使用者的memID
		Integer memID = mem.getMemID();
	
		return planOrdSvc.addPlanOrd(req, memID);
	}

	// amend data, for ajax using
	@CrossOrigin("*")
	@PutMapping("/updating")
	public PlanOrd updatePlanOrd(@RequestBody PlanOrd req) {
		return planOrdSvc.updatePlanOrd(req);
	}
	
	// list data, for ajax using
	@CrossOrigin("*")
	@GetMapping("/all")
	public List<PlanOrd> getAllPlanOrds(){
		return planOrdSvc.getAllPlanOrd();
	}	
	
	// create drop down menu
	@CrossOrigin("*")
	@GetMapping("/shopping")
	public List<Object[]> getAllPlanNames() {
		return planOrdSvc.findDistinctPlanNamesAndPrice();
	}
	
//	不做刪除功能
//	@CrossOrigin("*")
//	@DeleteMapping("deleting")
//	public String deletePlanOrd(@RequestBody PlanOrd req) {
//		planOrdSvc.deletePlanOrd(req.getPlanID());
//		return "deleted seccessfully";
//	}

	
//	@CrossOrigin("*")
//	@GetMapping("/{planOrdID}")
//	public PlanOrd getPlanOrdById(@PathVariable Integer planOrdID) {
//		return planOrdSvc.getPlanOrdById(planOrdID);
//	}

}
