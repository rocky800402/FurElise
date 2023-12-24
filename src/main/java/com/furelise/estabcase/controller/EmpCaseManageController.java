package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpCaseManageService;
import com.furelise.estabcase.empcasemanage.EmpCaseManageVO;
import com.furelise.estabcase.model.EstabCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/estabcasemanage")
public class EmpCaseManageController {
    @Autowired
    private EmpCaseManageService empCaseManageService;

    @GetMapping("/")
    public String backEndEstabcase(){
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

