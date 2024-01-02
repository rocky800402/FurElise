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

    //上線環境用
//    @GetMapping("/")
//    public String backEndEstabcase(@RequestParam(name = "empID", required = false) Integer empID, ModelMap model) {
//        // 目前前端頁面用的json請求(EstabCaseManagerRESTCon)是寫死的 只會查120001
//        model.addAttribute("empID", empID);
//        if(empID != null){
//            return "jack_caseManagement";
//        }else {
//            return "login";
//        }
//    }

    @GetMapping("/")
    public String backEndEstabcase(HttpSession session, ModelMap model){

        Emp emp = (Emp) session.getAttribute("emp");//從session中獲取emp物件
        model.addAttribute("empID",emp.getEmpID());//送到前端做使用

        return "jack_caseManagement";
    }

//    @GetMapping({"/", "/{empID}"})
//    public String backEndEstabcase(@PathVariable(name = "empID", required = false) Integer empID, ModelMap model) {
//        if (empID != null) {
//            List<EmpCaseManageVO> estabCase = empCaseManageService.getEmpEstabCase(empID);
//
//            if (estabCase != null) {
//
//                List<EmpCaseManageVO> estabCase0 = new ArrayList<>();
//                List<EmpCaseManageVO> estabCase1 = new ArrayList<>();
//
//                for (EmpCaseManageVO empCase : estabCase) {
//                    Integer estabCaseStatus = empCase.getEstabCaseStatus();
//                    if (estabCaseStatus == 0) {
//                        estabCase0.add(empCase);
//                    } else if (estabCaseStatus == 1) {
//                        estabCase1.add(empCase);
//                    }
//                }
//                model.addAttribute("estabCase0", estabCase0);
//                model.addAttribute("estabCase1", estabCase1);
//            } else {
//                model.addAttribute("errorMessage", "查無資料");
//                return "redirect:/login";
//            }
//        }
//        return "jack_caseManagement";
//    }

}

