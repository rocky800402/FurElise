package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpCompletedCaseService;
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
    EmpCompletedCaseService empCompletedCaseServer;

    @GetMapping("/{estabcaesID}")
    public EmpCompletedCaseVO goCompletedPage(@PathVariable(name = "estabcaesID") Integer estabcaesID, ModelMap model) {

        return empCompletedCaseServer.showCompletedCase(estabcaesID);
    }
}
