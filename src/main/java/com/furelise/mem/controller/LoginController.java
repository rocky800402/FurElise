package com.furelise.mem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
    @CrossOrigin("*")
    @GetMapping("/member_home_alter/{memID}")
	public String memInfo(@PathVariable("memID") Integer memID, Model model, HttpSession session) {
		Mem mem = (Mem) session.getAttribute("mem");
		model.addAttribute("mem", mem);
		if (mem != null) {
	        return "member_home_alter";
	    } else {
	        // 如果 mem 為 null，可能是未登入或登入訊息過期
	        // 這裡可以加入相應的處理邏輯，例如重定向到登入頁面
	        return "redirect:/login";
	    }
	}
    
    @CrossOrigin("*")
    @GetMapping("/emp_home_alter/{empID}")
	public String empInfo(@PathVariable("empID") Integer empID, Model model, HttpSession session) {
		Emp emp = (Emp) session.getAttribute("emp");
		model.addAttribute("emp", emp);
		if (emp != null) {
	        return "emp_home_alter";
	    } else {
	        // 如果 emp 為 null，可能是未登入或登入訊息過期
	        // 這裡可以加入相應的處理邏輯，例如重定向到登入頁面
	        return "redirect:/login";
	    }
	}
	
}
