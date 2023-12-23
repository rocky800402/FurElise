package com.furelise.vacation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furelise.vacation.model.entity.Vacation;
import com.furelise.vacation.service.VacationService;

@Controller
@RequestMapping("/vacation")
public class VacationController {

	@Autowired
	private VacationService vaSvc;
	
	@GetMapping("/{empID}")
    public String showEmpVacationPage(@PathVariable("empID") Integer empID/*, Model model*/) {
        return "emp-vacation";
	}
	
	@GetMapping("/{empID}/all")
	@ResponseBody
	public List<Vacation> getEmpVacation(@PathVariable("empID") Integer empID) {
		return vaSvc.getAllVacationDesc(empID);
	}


}
