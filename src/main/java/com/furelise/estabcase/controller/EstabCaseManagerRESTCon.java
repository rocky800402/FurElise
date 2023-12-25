package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpCaseManageService;
import com.furelise.estabcase.empcasemanage.EmpCaseManageVO;
import com.furelise.estabcase.model.EstabCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estabcase")
public class EstabCaseManagerRESTCon {

    @Autowired
    private EmpCaseManageService empCaseManageService;

    //調用這個API進行狀態分類
    @GetMapping("/{empID}/{estabCaseStatus}")
    @ResponseBody
    public List<EmpCaseManageVO> backEndEstabcase(
            @PathVariable(name = "empID") Integer empID,
            @PathVariable(name = "estabCaseStatus") Integer estabCaseStatus) {
        if (empID != null) {
            List<EmpCaseManageVO> estabCase = empCaseManageService.getEmpEstabCase(empID, estabCaseStatus);

            if (estabCase != null) {
                return estabCase;
            }
        }
        return Collections.emptyList();
    }

//    @GetMapping({"/", "/{empID}"})
//    @ResponseBody
//    public List<EmpCaseManageVO> backEndEstabcase1(
//            @PathVariable(name = "empID", required = false) Integer empID,
//            @PathVariable(name = "estabCaseStatus", required = false) Integer estabCaseStatus,
//            ModelMap model) {
//        List<EmpCaseManageVO> estabCase = empCaseManageService.getEmpEstabCase(empID, estabCaseStatus);
//
//        if (estabCase != null) {
//
//            if (estabCaseStatus != null) {
//                List<EmpCaseManageVO> filteredList = estabCase.stream()
//                        .filter(empCase -> empCase.getEstabCaseStatus().equals(estabCaseStatus))
//                        .collect(Collectors.toList());
//
//
//                return filteredList;
//            } else {
//                // estabCaseStatus
//                return estabCase;
//            }
//        } else {
//            // estabCase
//            return Collections.emptyList();
//        }
//    }


    @PostMapping("/updateCaseStatus")
    public EstabCase updateStatus(
            @RequestParam String action,
            @RequestParam Integer estabCaseID) {
        EstabCase estabCase = null;
        if (!action.isEmpty()) {
            if ("accept".equals(action)) {
                System.out.println("接受");
                estabCase = empCaseManageService.updateEstabCase(estabCaseID, true, 0);
            } else if ("reject".equals(action)) {
                System.out.println("拒絕");
                estabCase = empCaseManageService.setNullForEmpID(estabCaseID, false, 3);
            }
        }
        return estabCase;
    }
}
