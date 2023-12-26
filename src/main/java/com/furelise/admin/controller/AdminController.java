package com.furelise.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

	@GetMapping("/adminlogin")
	public String showPage() {
		return "admin-login";
	}
	
	@GetMapping("/adminlogout")
	public String logoutPage() {
		return "admin-logout";
	}
	
	// 測試用
	@GetMapping("/backendsidebar")
	public String backendSidebar() {
		return "backendsidebar";
	}
	
}
