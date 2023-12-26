package com.furelise.emp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.city.model.City;
import com.furelise.emp.model.*;
import com.furelise.mem.model.entity.Mem;

@Controller
@RequestMapping("/emp")
public class BackEndEmpController {

	@Autowired
	BackEndEmpService empSvc;
	
	@GetMapping("/list")
	public String empList(Model model) {
		model.addAttribute("empList", empSvc.getAllEmp());
		return "f_empData1"; //後台夥伴列表html
	}
	
	@GetMapping("/empchecklist")
	public String empCheckList(Model model) {
		model.addAttribute("empcheckList", empSvc.getAllEmp());
		return "f_empData2"; //後台夥伴列表html
	}
	
	@PostMapping("/getOne")
	public String getOne(@RequestParam Integer empID, Model model) {
		Emp emp = empSvc.getEmpById(empID);
		model.addAttribute("emp", emp);
		return ""; //單筆詳情html，可搭配修改功能
	}
	
	@PostMapping("/update") //修改欄位按下送出
	public String update(@RequestParam Integer empID , Model model) {
		Emp emp = empSvc.getOneEmp(empID);
		model.addAttribute("emp",emp);
		return "f_empbackend";
	}
	
	@PostMapping("/check") //修改欄位按下送出
	public String check(@RequestParam Integer empID , Model model) {
		System.out.println(empID);
		Emp emp = empSvc.getOneEmp(empID);
		
		model.addAttribute("emp",emp);
		return "f_empCheck";
	}
//	public String updateEmp(@Valid @ModelAttribute Emp emp, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return ""; // 如果有錯，秀出錯誤訊息，停留在修改html
//		} else {
//			empSvc.updateEmp(emp);
//			return ""; // 更新成功跳轉至某個html
//		}
//	}
	
	@PostMapping("/edit")
	public String edit(@RequestParam Integer empID, Model model) {
		Emp emp = empSvc.getOneEmp(empID);
		emp.setEmpIsSuspended(emp.getEmpIsSuspended() == true?false:true);
		empSvc.updateEmp(emp);
		model.addAttribute("empList",empSvc.getAllEmp());
		return "redirect:list";
	}
	
	@PostMapping("/changeStatus")
	public String changeStatus(@RequestParam Integer empID,
			@RequestParam Integer newStatus,Model model) {
	    // 取得指定ID的員工
	    Emp emp = empSvc.getOneEmp(empID);
	    
	    // 設置新的審核狀態
	    emp.setEmpStatus(newStatus);
	    
	    // 更新員工信息
	    empSvc.updateEmp(emp);
	    
	    // 向視圖添加最新的員工列表
	    model.addAttribute("empList", empSvc.getAllEmp());
	    
	    // 重定向到列表頁面
	    return "redirect:empchecklist";
	}

}
