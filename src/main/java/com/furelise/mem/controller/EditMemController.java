package com.furelise.mem.controller;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.service.MemService;

import oracle.net.aso.m;

@Controller
@RequestMapping("/mem")
public class EditMemController {
	
	@Autowired
	MemService memSvc;
	
	@PostMapping("/update")
	public String update(@RequestParam Integer memID , Model model) {
		Mem mem = memSvc.getOneMem(memID);
		
		model.addAttribute("mem",mem);
		return "f_membackend";
	}
	
	@PostMapping("/edit")
	public String edit(@RequestParam Integer memID, Model model) {
		Mem mem = memSvc.getOneMem(memID);
		mem.setMemIsSuspended(mem.getMemIsSuspended() == true?false:true);
		memSvc.updateMem(mem);
		model.addAttribute("memList",memSvc.getAllMem());
		return "redirect:list";
	}
	
	// return view
	@GetMapping("/list")
	public String memList(Model model) {
		model.addAttribute("memList",memSvc.getAllMem());
		return "f_mem";
		
	}
	

	@PostMapping("/emp.do")
	public String createMem(@RequestBody Mem mem, HttpServletRequest req) {
//		
		
		
// 		req.getParameter("memName") -> mem.getXX
		
		List<String> errorMsgs = new LinkedList<>();
		
		String memName = mem.getMemName().trim();
		String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (memName == null || memName.trim().length() == 0) {
			errorMsgs.add("會員姓名請勿空白");
		} else if (!memName.trim().matches(memNameReg)) { // 以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		}
		
		String memMail = mem.getMemMail().trim();
		String memMailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		if (memMail == null || memMail.trim().length() == 0) {
			errorMsgs.add("會員信箱請勿空白");
		} else if (!memMail.trim().matches(memMailReg)) {
			errorMsgs.add("請確認信箱格式");
		}
		
		String memTel = mem.getMemTel().trim();
		String memTelRegString = "^09\\d{8}$";
		if (memTel == null || memTel.trim().length() == 0) {
			errorMsgs.add("會員電話請勿空白");
		} else if (!memTel.trim().matches(memMailReg)) {
			errorMsgs.add("請確認電話格式");
		}
		

		LocalDate memBirth = mem.getMemBirth();
		if (memBirth == null ) {
			errorMsgs.add("會員生日請勿空白");
		}
		
		String memPass = mem.getMemPass().trim();
		if (memPass == null || memPass.trim().length() == 0) {
			errorMsgs.add("密碼請勿空白");
		}

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			 req .setAttribute("mem",mem); // 含有輸入格式錯誤的empVO物件,也存入req
			return "/mem/addMem.jsp";
		}
		
		
		mem.setMemRegiDate(LocalDateTime.now());
		mem.setMemLastModified(LocalDateTime.now());
//		mem.setMemIsSuspended(false);
		
		return "/mem/listAllEmp.jsp";
		
	}
	
	
}
