package com.furelise.mem.controller;

import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.model.vo.MemVO;
import com.furelise.mem.service.AuthService;
import com.furelise.emp.model.Emp;
import com.furelise.emp.model.EmpLoginDTO;
import com.furelise.emp.model.EmpVO;
import com.furelise.emp.controller.EmpAuthService;
import com.furelise.mem.model.dto.MemLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmpAuthService empAuthService;
    
    @GetMapping("/login")
	public String login() {
		return "login";
	}

    @PostMapping("/login-test")
    public String memLoginTst() {
        return "login-test";
    }
    
    @PostMapping("/login/mem")
    public MemVO memLogin(@RequestBody @Validated MemLoginDTO dto, HttpServletRequest req) {
        Mem mem = this.authService.verify(dto, req);
        return new MemVO(mem);
    }

    @GetMapping("/mem/me")
    public MemVO memMe(HttpServletRequest req) {
        Mem mem = authService.validateMem(req);
        System.out.println(mem);
        return new MemVO(mem);
    }

    @PostMapping("/login/emp")
    public EmpVO empLogin(@RequestBody @Validated EmpLoginDTO dto, HttpServletRequest req) {
        Emp emp = this.empAuthService.verify(dto, req);
        return new EmpVO(emp);
    }
    
    @GetMapping("/logout/mem")
    public void memLogout(HttpServletRequest req) {
    	HttpSession session = req.getSession();
		session.invalidate();
        System.out.println("aaaa");
    }
    
    @GetMapping("/logout/emp")
    public void empLogout(HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	session.invalidate();
    }
    
}
