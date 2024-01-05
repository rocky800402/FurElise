package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpCompletedCaseService;
import com.furelise.estabcase.empcasemanage.EmpCompletedCaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@Controller
@RequestMapping("/completed")
public class EmpCompletedCaseController {

    @Autowired
    EmpCompletedCaseService empCompletedCaseServer;

    @GetMapping("/{estabCaseID}")
    public String GetCompletedPage(@PathVariable(name = "estabCaseID") Integer estabCaseID, ModelMap model) {


        EmpCompletedCaseVO CompletedCase = empCompletedCaseServer.showCompletedCase(estabCaseID);
        model.addAttribute("image", Base64.getEncoder().encodeToString(CompletedCase.getEstabCasePic()));
        model.addAttribute("CompletedCase", CompletedCase);


        return "jack_CompletedCase";
    }
}
