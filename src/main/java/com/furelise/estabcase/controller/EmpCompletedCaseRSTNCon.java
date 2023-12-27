package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpCaseManageVO;
import com.furelise.estabcase.empcasemanage.EmpCompletedCaseServer;
import com.furelise.estabcase.empcasemanage.EmpCompletedCaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/completedcase")
public class EmpCompletedCaseRSTNCon {

    @Autowired
    EmpCompletedCaseServer empCompletedCaseServer;

    @GetMapping("/{estabcaesID}")
    public EmpCompletedCaseVO goCompletedPage(@PathVariable(name = "estabcaesID") Integer estabcaesID, ModelMap model) {

//        EmpCompletedCaseVO empCaseManageVO = null;
//        model.addAttribute("estabcaesID",estabcaesID);
//        empCompletedCaseServer.showCompletedCase(estabcaesID);


        return empCompletedCaseServer.showCompletedCase(estabcaesID);
    }
}
