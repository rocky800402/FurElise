package com.furelise.estabcase.controller;

import com.furelise.emp.model.Emp;
import com.furelise.estabcase.empcasemanage.EmpCaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/estabcasemanage")
public class EstabCaseManageController {
    @Autowired
    private EmpCaseManageService empCaseManageService;


    @GetMapping("/")
    public String backEndEstabcase(HttpSession session, ModelMap model){

        Emp emp = (Emp) session.getAttribute("emp");//從session中獲取emp物件
        model.addAttribute("empID",emp.getEmpID());//送到前端做使用

        return "jack_caseManagement";
    }


}

