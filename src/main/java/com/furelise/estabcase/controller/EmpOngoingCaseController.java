package com.furelise.estabcase.controller;

import com.furelise.emp.model.EmpVO;
import com.furelise.estabcase.empcasemanage.EmpOngoingCaseService;
import com.furelise.estabcase.empcasemanage.EmpOngoingCaseVO;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import com.furelise.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/estabcaseonging")
public class EmpOngoingCaseController {

    @Autowired
    EmpOngoingCaseService empOngoingCaseService;
    @Autowired
    EstabCaseRepository estabCaseRepository;

    //進行中的案件 詳情跳轉
    @PostMapping("/details")
    public String getOngoingCasesInfo(
            @RequestParam(name = "estabCaseID") Integer estabCaseID,
            ModelMap model) {

        EmpOngoingCaseVO empOngoingCase = empOngoingCaseService.getEmpOngoingCase(estabCaseID);

        model.addAttribute("empOngoingCase", empOngoingCase);//給html做資料顯示
        model.addAttribute("estabCaseID", estabCaseID);

        return "jack_ongoingCases";
    }
}
