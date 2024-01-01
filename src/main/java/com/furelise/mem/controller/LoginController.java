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
	
    @GetMapping("/login-google")
    public String googleRegister(Model model, HttpSession session) {
    	Mem newMem = (Mem) session.getAttribute("newMem");
    	model.addAttribute("newMem", newMem);
    	return "login-google";
    }
    
    @GetMapping("/member/info")
	public String memInfo(Model model, HttpSession session) {
		Mem mem = (Mem) session.getAttribute("mem");
		model.addAttribute("mem", mem);
		if (session.getAttribute("google") != null) {
			boolean isGoogleLogin = (boolean) session.getAttribute("google");
			model.addAttribute("google", isGoogleLogin);
		}
		if (mem != null) {
	        return "member_home_alter";
	    } else {
	        // 如果 mem 為 null，可能是未登入或登入訊息過期
	        // 這裡可以加入相應的處理邏輯，例如重定向到登入頁面
	        return "redirect:/login";
	    }
	}
    
    
    @GetMapping("/emp/info")
	public String empInfo(Model model, HttpSession session) {
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
