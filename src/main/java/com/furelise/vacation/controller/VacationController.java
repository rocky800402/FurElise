package com.furelise.vacation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vacation")
public class VacationController {
	
	@GetMapping
    public String showEmpVacationPage() {
        return "emp-vacation";
	}

}
