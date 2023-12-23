package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpCaseManageService;
import com.furelise.estabcase.model.EmpCaseManageDTO;
import com.furelise.estabcase.model.EstabCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("/estabcasemanage")
public class EstabCaseManagerRESTCon {

    @Autowired
    private EmpCaseManageService empCaseManageService;

    @PostMapping("/updateCaseStatus")
    public EstabCase updateStatus(
            @RequestParam String action,
            @RequestParam Integer estabCaseID) {
        EstabCase estabCase = null;
        if(!action.isEmpty()){
            if ("accept".equals(action)) {
                estabCase = empCaseManageService.updateEstabCase(estabCaseID,true,0);
            } else if ("reject".equals(action)) {
                estabCase = empCaseManageService.updateEstabCase(estabCaseID,false,3);
            }
        }
        return estabCase;
    }
}
