package com.furelise.estabcase.controller;


import com.furelise.common.model.ErrorMessageVO;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseLevelDTO;
import com.furelise.estabcase.model.EstabCaseService;
import com.furelise.estabcase.model.MemEstabCaseVO;

import com.furelise.planord.model.PlanOrdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/memestabcase")
public class MemEstabCaseController {
	@Autowired
	private EstabCaseService estabCaseService;

	@Autowired
	private PlanOrdRepository planOrdRepository;



	@GetMapping("/{id}/{no}")
	public List<EstabCase> getAllEstabCases(@PathVariable String id, @PathVariable String no){
		List<EstabCase> estabCaseList = estabCaseService.getAllEstabCase();
		System.out.println(estabCaseList);
		return estabCaseList;
	}

	@GetMapping("/{planOrdID}")
	public MemEstabCaseVO getAllMemEstabCase(
			@PathVariable Integer planOrdID){

		MemEstabCaseVO memEstabCaseVO = estabCaseService.getMemEstabCase(planOrdID);

		//拋出錯誤處理用
		planOrdRepository.findById(planOrdID).orElseThrow();

		return memEstabCaseVO;
	}
	@PatchMapping()
	public EstabCase updateEstabCaseLevel(
			@Validated @RequestBody EstabCaseLevelDTO estabCaseLevelDTO){
		return estabCaseService.updateEstabCaseLevel(estabCaseLevelDTO);
	}



}