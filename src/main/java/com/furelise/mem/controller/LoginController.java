package com.furelise.mem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.mem.model.entity.Mem;
import com.furelise.emp.model.Emp;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String memLogin() {
		return "login";
	}

    @GetMapping("/login-test")
    public String memLoginTst() {
        return "login-test";
    }
	
	@GetMapping("/member_home_alter/{mem}")
	public String memInfo(@RequestParam("mem") Mem mem , Model model) {
		model.addAttribute("mem", mem);
		return "member_home_alter";
	}
	
	@GetMapping("/emp_home_alter/{emp}")
	public String empInfo(@RequestParam("emp") Emp emp , Model model) {
		model.addAttribute("emp", emp);
		return "emp_home_alter";
	}
	
}
