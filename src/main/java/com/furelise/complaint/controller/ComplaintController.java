package com.furelise.complaint.controller;

import javax.validation.Valid;

import org.glassfish.jersey.spi.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.complaint.model.*;

@Controller
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	ComplaintService complaintSvc;

	@GetMapping("/all")
	public String complaintList(Model model) {
		model.addAttribute("complaintList", complaintSvc.getAllComplaint());
		return ""; //申訴列表html
	}

	@PostMapping("/getOne")
	public String getOne(@RequestParam Integer complaintID, Model model) {
		Complaint complaint = complaintSvc.getComplaintById(complaintID);
		model.addAttribute("complaint", complaint);
		return ""; // 單筆詳情html，可搭配修改功能
	}

	@PostMapping("/update") //修改欄位按下送出
	public String updateComplaint(@Valid @ModelAttribute Complaint complaint, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return ""; // 如果有錯，秀出錯誤訊息，停留在修改html
		} else {
			complaintSvc.updateComplaint(complaint);
			return ""; // 更新成功跳轉至某個html
		}
	}
}
