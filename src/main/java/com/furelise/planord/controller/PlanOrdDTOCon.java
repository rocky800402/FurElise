package com.furelise.planord.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.furelise.mem.model.entity.*;

@RestController
@RequestMapping("/planorddto")
public class PlanOrdDTOCon {

	@Autowired
	PlanOrdService planOrdSvc;

	// add data, for ajax using
	@PostMapping("/adding")
	public PlanOrd addPlanOrd(@RequestBody PlanOrdDTO dto, HttpServletRequest req) {

		Mem mem = (Mem)(req.getSession().getAttribute("account"));
//		Integer memID = mem.getMemID();
		//測試用寫死
		Integer memID = 110003;

		return planOrdSvc.addPlanOrd(dto, memID);
	}

	// list data, for ajax using
	@CrossOrigin("*")
	@GetMapping("/finding")
	public List<PlanOrdDTO> getAllPlanOrds() {
		return planOrdSvc.getPlanOrdInfo();
	}

	// update data, for ajax using

}
