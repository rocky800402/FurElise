package com.furelise.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.furelise.admin.model.dto.AdminLoginDTO;
import com.furelise.admin.model.entity.Admin;
import com.furelise.admin.model.vo.AdminVO;
import com.furelise.admin.service.AdminService;

@RestController
@RequestMapping("/api/auth")
public class AdminLoginController {

	@Autowired
	private AdminService adminSvc;
	
	@PostMapping("/login/admin")
	public AdminVO adminLogin(@RequestBody @Valid AdminLoginDTO dto, HttpServletRequest req) {
		Admin admin = this.adminSvc.verify(dto);
		HttpSession session = req.getSession();
		session.setAttribute("admin", admin);
		return new AdminVO(admin);
	}
	
	@GetMapping("/logout/admin")
	public void adminLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
	}
	
}
