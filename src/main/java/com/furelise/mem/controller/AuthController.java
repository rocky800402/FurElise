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
import org.springframework.ui.Model;
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
    
	
//    
//    @GetMapping("/member_home_alter/{memID}")
//	public String memInfo(@PathVariable("memID") Integer memID, Model model, HttpSession session) {
//		Mem mem = (Mem) session.getAttribute("mem");
//		if (mem != null) {
//	        return "member_home_alter";
//	    } else {
//	        // 如果 mem 為 null，可能是未登入或登入訊息過期
//	        // 這裡可以加入相應的處理邏輯，例如重定向到登入頁面
//	        return "redirect:/login";
//	    }
//	}
    
    
    @GetMapping("/emp_home_alter/{empID}")
	public String empInfo(@PathVariable("empID") Integer empID, Model model, HttpSession session) {
		Emp emp = (Emp) session.getAttribute("emp");
		if (emp != null) {
	        return "emp_home_alter";
	    } else {
	        // 如果 emp 為 null，可能是未登入或登入訊息過期
	        // 這裡可以加入相應的處理邏輯，例如重定向到登入頁面
	        return "redirect:/login";
	    }
	}

    
    @PostMapping("/login/mem")
    public MemVO memLogin(@RequestBody @Validated MemLoginDTO dto, HttpServletRequest req) {
        Mem mem = this.authService.verify(dto);
        HttpSession session = req.getSession();
        session.setAttribute("mem", mem);
        return new MemVO(mem);
    }

    
    @PostMapping("/login/emp")
    public EmpVO empLogin(@RequestBody @Validated EmpLoginDTO dto, HttpServletRequest req) {
        Emp emp = this.empAuthService.verify(dto);
        HttpSession session = req.getSession();
        session.setAttribute("emp", emp);
        return new EmpVO(emp);
    }
    
}
