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

    @GetMapping("/{estabCaseID}")
    public String getOngoingCasesInfo(
            @PathVariable(name = "estabCaseID")Integer estabCaseID, ModelMap model){

        EmpOngoingCaseVO empOngoingCase = empOngoingCaseService.getEmpOngoingCase(estabCaseID);

        model.addAttribute("empOngoingCase",empOngoingCase);
        model.addAttribute("estabCaseID",estabCaseID);

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

//    @PostMapping("insert")
//    public String insert(@Valid EstabCase estabCase, BindingResult result, ModelMap model,
//                         @RequestParam("upFiles") MultipartFile[] parts) throws IOException {
//
//        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ******************/
//        // 去除BindingResult中upFiles欄位的FieldError紀錄
//        List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
//                .filter(fieldname -> !fieldname.getField().equals("upFiles")).collect(Collectors.toList());
//        result = new BeanPropertyBindingResult(estabCase, "estabCase");
//        for (FieldError fieldError : errorsListToKeep) {
//            result.addError(fieldError);
//        }
//        if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
//            model.addAttribute("errorMessage", "請上傳執行結果");
//        } else {
//            for (MultipartFile multipartFile : parts) {
//                byte[] buf = multipartFile.getBytes();
//                estabCase.setEstabCasePic(buf);
//            }
//        }
//        if (result.hasErrors() || parts[0].isEmpty()) {
//            return "jack_ongoingCases";
//        }
//        /*************************** 2.開始新增資料 ***************************************/
////		EmpService empSvc = new EmpService();
//        estabCaseRepository.save(estabCase);
////        /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
////        List<EmpVO> list = empSvc.getAll();
////        model.addAttribute("empListData", list);
////        model.addAttribute("success", "- (新增成功)");
//        return "redirect:/jack_caseManagement"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
//    }
}
