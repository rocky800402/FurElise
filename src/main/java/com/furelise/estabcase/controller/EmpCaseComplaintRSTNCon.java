package com.furelise.estabcase.controller;

import com.furelise.complaint.model.Complaint;
import com.furelise.complaint.model.ComplaintService;
import com.furelise.emp.model.EmpRepository;
import com.furelise.estabcase.empcasemanage.EmpCaseManageService;
import com.furelise.estabcase.model.EstabCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@RequestMapping("/estabcaseonging")
public class EmpCaseComplaintRSTNCon {
    @Autowired
    ComplaintService complaintService;
    @Autowired
    EmpCaseManageService empCaseManageService;


    //夥伴申訴表單儲存
    @PostMapping("/complaint")
    public void ReturnComplaint(@Valid @RequestBody Complaint complaint) {

        // 抓系統時間
        java.util.Date currentDate = new java.util.Date();

        // 放入 Timestamp 物件
        Timestamp comStartTime = new Timestamp(currentDate.getTime());

        // 把comStartTime 存入 complaint 中的 comStart
        complaint.setComStart(comStartTime);
        complaint.setComEnd(null);
        complaintService.updateComplaint(complaint);
    }
    @PostMapping("/updateCaseStatus")
    public EstabCase updateStatus(
            @RequestParam String action,
            @RequestParam Integer estabCaseID) {
        EstabCase estabCase = null;
        if (!action.isEmpty()) {
            if ("accept".equals(action)) {
                estabCase = empCaseManageService.updateEstabCase(estabCaseID, true, 0);
            } else if ("reject".equals(action)) {
                estabCase = empCaseManageService.setNullForEmpID(estabCaseID, false, 3);
            }
        }
        return estabCase;
    }
}
