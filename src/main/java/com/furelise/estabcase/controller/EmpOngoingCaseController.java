package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpOngoingCaseService;
import com.furelise.estabcase.empcasemanage.EmpOngoingCaseVO;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import com.furelise.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/estabcaseonging")
public class EmpOngoingCaseController {

    @Autowired
    EmpOngoingCaseService empOngoingCaseService;
    @Autowired
    EstabCaseRepository estabCaseRepository;

    @GetMapping("/{estabCaseID}")
    public String getOngoingCasesInfo(
            @PathVariable(name = "estabCaseID")Integer estabCaseID, ModelMap model){

        EmpOngoingCaseVO empOngoingCase = empOngoingCaseService.getEmpOngoingCase(estabCaseID);

        model.addAttribute("empOngoingCase",empOngoingCase);

        return "jack_ongoingCases";
    }
    @GetMapping("/showImage")
    public String showImage(@PathVariable(name = "estabCaseID")Integer estabCaseID,Model model) {
        // Logic to retrieve image from the database
        EstabCase estabCase = estabCaseRepository.findById(estabCaseID).orElse(null);
        model.addAttribute("image", estabCase != null ? estabCase.getEstabCasePic() : null);
        return "jack_ongoingCases";
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        EstabCase estabCase = new EstabCase();
        estabCase.setEstabCasePic(file.getBytes());
        estabCaseRepository.save(estabCase);
        return "redirect:/showImage";
    }

//    @PostMapping("/add")
//    public String statusPicSubmit(@Valid EstabCase estabCase, BindingResult result, ModelMap model) {
//        if(result.hasErrors()) {
//            return "b-product-add";
//        } else {
//            boolean isPass = empOngoingCaseService.addStstusPicture(estabCase);
//            if(isPass) {
//                return "redirect:/product-backen/all";
//            } else {
//                model.addAttribute("errorMsgs", "商品名稱重複");
//                return "b-product-add";
//            }
//        }
//    }
}
