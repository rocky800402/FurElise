package com.furelise.estabcase.controller;

import com.furelise.complaint.model.Complaint;
import com.furelise.complaint.model.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@RequestMapping("/estabcaseonging")
public class EmpCaseComplaintRSTNCon {
    @Autowired
    ComplaintService complaintService;


    //夥伴申訴表單儲存
    @PostMapping("/complaint")
    public void ReturnComplaint(@Valid @RequestBody Complaint complaint) {

        // 抓系統時間
        java.util.Date currentDate = new java.util.Date();

        // 放入 Timestamp 物件
        Timestamp comStartTime = new Timestamp(currentDate.getTime());

        // 把comStartTime 存入 complaint 中的 comStart
        complaint.setComStart(comStartTime);
        complaintService.updateComplaint(complaint);
    }
}
