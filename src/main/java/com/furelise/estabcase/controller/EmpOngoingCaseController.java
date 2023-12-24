package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpOngoingCaseService;
import com.furelise.estabcase.empcasemanage.EmpOngoingCaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estabcaseonging")
public class EmpOngoingCaseController {

    @Autowired
    EmpOngoingCaseService empOngoingCaseService;

    @GetMapping("/{estabCaseID}")
    public String getOngoingCasesInfo(
            @PathVariable(name = "estabCaseID")Integer estabCaseID, ModelMap model){

        EmpOngoingCaseVO empOngoingCase = empOngoingCaseService.getEmpOngoingCase(estabCaseID);

        model.addAttribute("empOngoingCase",empOngoingCase);
        System.out.println(empOngoingCase.getWayName());

        return "jack_ongoingCases";
    }
}
