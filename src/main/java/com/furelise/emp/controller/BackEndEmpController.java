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

@Controller
@RequestMapping("/emp")
public class BackEndEmpController {

	@Autowired
	BackEndEmpService bemSvc;
	
	@GetMapping("/all")
	public String empList(Model model) {
		model.addAttribute("empList", bemSvc.getAllEmp());
		return ""; //後台夥伴列表html
	}
	
	@PostMapping("/getOne")
	public String getOne(@RequestParam Integer empID, Model model) {
		Emp emp = bemSvc.getEmpById(empID);
		model.addAttribute("emp", emp);
		return ""; //單筆詳情html，可搭配修改功能
	}
	
	@PostMapping("/update") //修改欄位按下送出
	public String updateEmp(@Valid @ModelAttribute Emp emp, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return ""; // 如果有錯，秀出錯誤訊息，停留在修改html
		} else {
			bemSvc.updateEmp(emp);
			return ""; // 更新成功跳轉至某個html
		}
	}
}
