package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpCaseManageService;
import com.furelise.estabcase.empcasemanage.EmpOngoingCaseService;
import com.furelise.estabcase.empcasemanage.EmpOngoingCaseVO;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;

@RestController
@RequestMapping("/estabcaseongo")
public class EmpOngoingCaseRSTNCon {

    @Autowired
    EstabCaseRepository estabCaseRepository;
    @Autowired
    private EmpOngoingCaseService empOngoingCaseService;

//    @PostMapping("/{estabCaseID}")
//    public EstabCase uploadFile(@PathVariable Integer estabCaseID,
//                                @RequestParam MultipartFile file,
//                                ModelMap model) throws IOException {
//
////        EmpOngoingCaseVO empOngoingCase = (EmpOngoingCaseVO) model.getAttribute("empOngoingCase");
////        EstabCase estabCase = estabCaseRepository.findById(empOngoingCase.getEstabCaseID()).orElseThrow();
//        EstabCase estabCase = estabCaseRepository.findById(estabCaseID).orElseThrow();
//        estabCase.setEstabCasePic(file.getBytes());
//        return estabCaseRepository.save(estabCase);
//    }

    @PostMapping("/{estabCaseID}")
    public EstabCase uploadFile(@PathVariable Integer estabCaseID,
                                @RequestParam MultipartFile file) throws IOException {

//        EmpOngoingCaseVO empOngoingCase = (EmpOngoingCaseVO) model.getAttribute("empOngoingCase");
//        EstabCase estabCase = estabCaseRepository.findById(empOngoingCase.getEstabCaseID()).orElseThrow();
        EstabCase estabCase = estabCaseRepository.findById(estabCaseID).orElseThrow();
        estabCase.setEstabCasePic(file.getBytes());
        estabCase.setEstabCaseStatus(1);//設定案件狀態

        // 抓系統時間
        java.util.Date currentDate = new java.util.Date();
        // 放入 Timestamp 物件
        Timestamp completedTime = new Timestamp(currentDate.getTime());
        estabCase.setEstabCaseEnd(completedTime);
        return estabCaseRepository.save(estabCase);
    }

    @PostMapping("/completed")
    public void CompleteCase(@RequestParam Integer estabCaseID){
        empOngoingCaseService.CompleteCase(estabCaseID);
    }
}
