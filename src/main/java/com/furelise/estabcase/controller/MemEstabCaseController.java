package com.furelise.estabcase.controller;


import com.furelise.common.model.ErrorMessageVO;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseLevelDTO;
import com.furelise.estabcase.model.EstabCaseService;
import com.furelise.estabcase.model.MemEstabCaseVO;

import com.furelise.mem.model.entity.Mem;
import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/mem-estab-case")
public class MemEstabCaseController {
	@Autowired
	private EstabCaseService estabCaseService;

	@Autowired
	private PlanOrdRepository planOrdRepository;

	@GetMapping
	public MemEstabCaseVO getAllMemEstabCase( HttpServletRequest req){
//		System.out.println(req);
//		System.out.println(req.getSession());
		Mem mem = (Mem) req.getSession().getAttribute("account");
//		System.out.println("mem");
//		System.out.println(mem);
		PlanOrd planOrd = planOrdRepository.findByMemIDAndPlanStatusID(mem.getMemID(), 210001);
        return estabCaseService.getMemEstabCase(planOrd.getPlanOrdID());
	}


	@GetMapping("/{id}/{no}")
	public List<EstabCase> getAllEstabCases(@PathVariable String id, @PathVariable String no){
		List<EstabCase> estabCaseList = estabCaseService.getAllEstabCase();
		System.out.println(estabCaseList);
		return estabCaseList;
	}


	@PatchMapping()
	public EstabCase updateEstabCaseLevel(
			@Validated @RequestBody EstabCaseLevelDTO estabCaseLevelDTO){
		return estabCaseService.updateEstabCaseLevel(estabCaseLevelDTO);
	}



}
