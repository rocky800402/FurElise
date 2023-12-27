package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpCompletedCaseServer;
import com.furelise.estabcase.empcasemanage.EmpCompletedCaseVO;
import com.furelise.estabcase.empcasemanage.EmpOngoingCaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@Controller
@RequestMapping("/completed")
public class EmpCompletedCaseController {

    @Autowired
    EmpCompletedCaseServer empCompletedCaseServer;

    @GetMapping("/{estabCaseID}")
    public String GetCompletedPage(@PathVariable(name = "estabCaseID") Integer estabCaseID, ModelMap model) {


        EmpCompletedCaseVO CompletedCase = empCompletedCaseServer.showCompletedCase(estabCaseID);
        model.addAttribute("image", Base64.getEncoder().encodeToString(CompletedCase.getEstabCasePic()));
        model.addAttribute("CompletedCase", CompletedCase);
//        empCompletedCaseServer.showCompletedCase(estabCaseID);


        return "jack_CompletedCase";
    }
//    @PostMapping("/{estabcaesID}")
//    public String GetCompletedPage(@PathVariable(name = "estabcaesID") Integer estabcaesID, ModelMap model) {
//
//
//        EmpCompletedCaseVO CompletedCase = empCompletedCaseServer.showCompletedCase(estabcaesID);
//        model.addAttribute("image", Base64.getEncoder().encodeToString(CompletedCase.getEstabCasePic()));
//        model.addAttribute("CompletedCase", CompletedCase);
////        empCompletedCaseServer.showCompletedCase(estabcaesID);
//
//
//        return "jack_CompletedCase";
//    }

//    @PostMapping("/test")
//    public String goCompletedPage(@RequestParam(name = "estabCaseID") Integer estabCaseID, ModelMap model) {
//
//
//        EmpCompletedCaseVO CompletedCase = empCompletedCaseServer.showCompletedCase(estabCaseID);
//        model.addAttribute("image", Base64.getEncoder().encodeToString(CompletedCase.getEstabCasePic()));//給html做圖片顯示，需要解碼
//        model.addAttribute("CompletedCase", CompletedCase);//給html做資料顯示
//
//        return "jack_CompletedCase";
//    }

    //進行中的案件 詳情跳轉
//    @PostMapping("/completeddetails")
//    public String getOngoingCasesInfo(
//            @RequestParam(name = "estabCaseID") Integer estabCaseID,
//            ModelMap model) {
//
//        EmpCompletedCaseVO empOngoingCase = empCompletedCaseServer.showCompletedCase(estabCaseID);
//
//        model.addAttribute("empOngoingCase", empOngoingCase);
//        model.addAttribute("estabCaseID", estabCaseID);
//
//        return "jack_CompletedCase";
//    }
}
