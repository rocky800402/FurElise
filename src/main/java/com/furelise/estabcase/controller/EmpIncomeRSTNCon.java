package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpIncomeServer;
import com.furelise.estabcase.empcasemanage.IncomeSummaryDTO;
import com.furelise.estabcase.model.EstabCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/income")
public class EmpIncomeRSTNCon {

    @Autowired
    EmpIncomeServer empIncomeServer;

    @GetMapping("/{empID}/{estabCaseStatus}")
    public List<EstabCase> IncomeInformation(
            @PathVariable(name = "empID") Integer empID,
            @PathVariable(name = "estabCaseStatus") Integer estabCaseStatus) {
        if (empID != null) {
            List<EstabCase> estabCase = empIncomeServer.getIncomeInformatin(empID, estabCaseStatus);

            if (estabCase != null) {
                return estabCase;
            }
        }
        return Collections.emptyList();
    }

    //統計案件數與收入API
//    @GetMapping("/mappingInf")
//    public ResponseEntity<Map<String, Object>> getMappingInf(
//            @RequestParam Integer empID,
//            @RequestParam Integer estabCaseStatus) {
//
//        Map<String, Object> mappingInf = empIncomeServer.getTotalInf(empID, estabCaseStatus);
//
//        return ResponseEntity.ok(mappingInf);
//    }

    //統計案件數與收入API
    @PostMapping("/mappingInf")
    public ResponseEntity<Map<String, Object>> getMappingInf(
//            @RequestParam Integer empID
            @RequestBody Map<String, Integer> params
    ) {

        System.out.println(params);

        int empID = params.get("empID");
        int year = params.get("year");
        int month = params.get("month");

        Map<String, Object> mappingInf = empIncomeServer.getTotalInf(empID, year, month);

        return ResponseEntity.ok(mappingInf);
    }

    //    @PostMapping("/byMonth")
//    public List<EstabCase> getEstabCasesByMonth(@RequestParam int year, @RequestParam int month) {
//        return empIncomeServer.getEstabCasesByMonth(year, month);
//    }
    @PostMapping("/byMonth")
    public List<EstabCase> EstabCasesByMonthAndStatus(@RequestBody Map<String, Integer> params) {

        int year = params.get("year");
        int month = params.get("month");

        return empIncomeServer.getEstabCasesByMonth(year, month);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<IncomeSummaryDTO>> getIncomeSummary(
            @RequestParam Integer empID) {

        List<IncomeSummaryDTO> incomeSummaryList = empIncomeServer.getIncomeSummary(empID);

        return new ResponseEntity<>(incomeSummaryList, HttpStatus.OK);
    }
}

