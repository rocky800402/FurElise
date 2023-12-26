package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpOngoingCaseVO;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/estabcaseongo")
public class EmpOngoingCaseRSTNCon {

    @Autowired
    EstabCaseRepository estabCaseRepository;

    @PostMapping("/{estabCaseID}")
    public EstabCase uploadFile(@PathVariable Integer estabCaseID,
                                @RequestParam MultipartFile file,
                                ModelMap model) throws IOException {

//        EmpOngoingCaseVO empOngoingCase = (EmpOngoingCaseVO) model.getAttribute("empOngoingCase");
//        EstabCase estabCase = estabCaseRepository.findById(empOngoingCase.getEstabCaseID()).orElseThrow();
        EstabCase estabCase = estabCaseRepository.findById(estabCaseID).orElseThrow();
        estabCase.setEstabCasePic(file.getBytes());
        return estabCaseRepository.save(estabCase);
    }
}
