package com.furelise.estabcase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseService;

@RestController
@RequestMapping("/memestabcase")
public class MemEstabCaseController {
	@Autowired
	private EstabCaseService estabCaseService;
	
	
	@CrossOrigin("*")
	@GetMapping("/{id}/{no}")
	public List<EstabCase> getAllEstabCases(@PathVariable String id,@PathVariable String no){
		List<EstabCase> estabCaseList = estabCaseService.getAllEstabCase();
		System.out.println(estabCaseList);
		return estabCaseList;
		
	}
	
	@CrossOrigin("*")
	@GetMapping("/{planordid}")
	public List<EstabCase> getAllMemEstabCase(
			@PathVariable String planordid
			
			){
		Integer planOrdID = Integer.valueOf(planordid);
		System.out.println(planordid);
		List<EstabCase> estabCaseList = estabCaseService.getEstabCaseByPlanOrdID(planOrdID);
	
		System.out.println(estabCaseList);
		return estabCaseList;
		
	}
	
}
