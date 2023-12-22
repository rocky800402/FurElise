package com.furelise.vacation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.emp.model.Emp;
import com.furelise.vacation.model.dto.VacationDTO;
import com.furelise.vacation.model.entity.Vacation;
import com.furelise.vacation.service.VacationService;

@RestController
@RequestMapping("/api/vacation")
public class AddVaController {
	
	@Autowired
	private VacationService vaSvc;

	@PostMapping("/{empID}/add")
	public Vacation addVacation(@RequestBody @Validated VacationDTO dto, @PathVariable("empID") Integer empID) {
        // 調用 Service 的add方法
        return vaSvc.addVacation(empID, dto.getVaStart(), dto.getVaEnd());
	}
	
}
