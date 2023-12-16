package com.furelise.planord.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.planord.model.*;
import com.furelise.mem.model.*;

@RestController
@RequestMapping("/planorddto")
public class PlanOrdDTOCon {

	@Autowired
	PlanOrdService planOrdSvc;

	// add data, for ajax using
	@PostMapping("/adding")
	public PlanOrd addPlanOrd(@RequestBody PlanOrdDTO req) {
//		//	驗證使用者是否登入

//		//	取得使用者的memID
//		Integer memID = mem.getMemID();
		Integer memID = 110005;

		return planOrdSvc.addPlanOrd(req, memID);
	}

	// list data, for ajax using
	@CrossOrigin("*")
	@GetMapping("/finding")
	public List<PlanOrdDTO> getAllPlanOrds() {
		return planOrdSvc.getPlanOrdInfo();
	}

	// update data, for ajax using

}
