package com.furelise.mem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.emp.controller.EmpAuthService;
import com.furelise.emp.model.Emp;
import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.service.MemService;
import com.furelise.mem.service.ValidateService;

@Controller
@RequestMapping
public class ForgetpwController {

	@Autowired
	private ValidateService validSvc;
	
	@Autowired
	private MemService memSvc;
	
	@Autowired
	private EmpAuthService empSvc;
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	
	@GetMapping("/getpw")
	public String getPW (@RequestParam("token") String token, Model model) {
		model.addAttribute("token", token);
		return "resetpw-resetpage";
	}
	
	@PostMapping("/forgetpw")
	public String forgetpw (HttpServletRequest req, Model model, HttpSession session) {
		
		String memMail = req.getParameter("memForgetPW");
		String empMail = req.getParameter("empForgetPW");
		String memMsgs = null;
		String empMsgs = null;
		String resetMsgs = null;
		
//		System.out.println(memMail);
//		System.out.println(empMail);
		session.setAttribute("memMail", memMail);
		session.setAttribute("empMail", empMail);
		
		
		if (memMail != null) {
			// ===用戶填寫了會員的忘記密碼===
			// 檢查會員是否存在
			Mem resetMem = memSvc.findByMemMail(memMail);
			if (resetMem == null) {
				memMsgs = "找不到相符的帳戶，請仔細檢查您輸入的內容是否有誤，或者嘗試使用其他電子郵件地址。";
				model.addAttribute("memMsgs", memMsgs);
				return "login";
			}
			
			// 找到該會員，寄發重設密碼信件
			validSvc.sendResetPWMail(memMail);
			return "resetpw-sendingsuccess";
			
		} else if (empMail != null) {
			// ===用戶填寫了夥伴的忘記密碼===
			// 檢查夥伴是否存在
			Emp resetEmp = empSvc.findByEmpMail(empMail);
			if (resetEmp == null) {
				empMsgs = "找不到相符的帳戶，請仔細檢查您輸入的內容是否有誤，或者嘗試使用其他電子郵件地址。";
				model.addAttribute("empMsgs", empMsgs);
				return "login";
			}
			
			// 找到該夥伴，寄發重設密碼信件
			validSvc.sendResetPWMail(empMail);
			return "resetpw-sendingsuccess";
			
		} else {
			
			resetMsgs = "找不到相符的帳戶。";
			model.addAttribute("resetMsgs", resetMsgs);
			return "login";
			
		}
		
	}
	
	
	@PostMapping("/resetpw")
	public String resetpw (HttpServletRequest req, Model model, HttpSession session) {
		
		String newPW = req.getParameter("newPW");
		String cfmPW = req.getParameter("confirmNewPW");
		String token = req.getParameter("token");
		String errMsgs = "";
		
		String memMail = (String) session.getAttribute("memMail");
		String empMail = (String) session.getAttribute("empMail");
		session.removeAttribute("memMail");
		session.removeAttribute("empMail");
		
		// ===錯誤驗證；新密碼與確認密碼必須相同===
		if (!newPW.equals(cfmPW)) {
			errMsgs = "新密碼與確認密碼不相同，請再輸入一次！";
			model.addAttribute("errMsgs", errMsgs);
			return "resetpw-resetpage";
		}
		
		if (memMail != null) {
			// ===修改會員密碼===
			// 1. 取得redis裡已存在的token
			String existToken = redisTemplate.opsForValue().get("ResetMail:" + memMail);
			// 2. 比對url取得的token，token相同則進行密碼修改
			if (token.equals(existToken)) {
				Mem mem = memSvc.findByMemMail(memMail);
				mem.setMemPass(newPW);
				memSvc.updateMem(mem);
				return "resetpw-resetsuccess";
			}
			
		} else if (empMail != null) {
			// ===修改夥伴密碼===
			// 1. 取得redis裡已存在的token
			String existToken = redisTemplate.opsForValue().get("ResetMail:" + empMail);
			// 2. 比對url取得的token，token相同則進行密碼修改
			if (token.equals(existToken)) {
				Emp emp = empSvc.findByEmpMail(empMail);
				emp.setEmpPass(newPW);
				empSvc.updateEmp(emp);
				return "resetpw-resetsuccess";
			}
			
		}
		
		errMsgs = "重設密碼失敗，請回到登入頁面 → 忘記密碼，再試一次。";
		model.addAttribute("errMsgs", errMsgs);
		return "resetpw-resetpage";
		
	}
	
}
