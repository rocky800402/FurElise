package com.furelise.estabcase.controller;

import com.furelise.estabcase.empcasemanage.EmpIncomeServer;
import com.furelise.estabcase.model.EstabCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    @GetMapping("/mappingInf")
    public ResponseEntity<Map<String, Object>> getMappingInf(
            @RequestParam Integer empID,
            @RequestParam Integer estabCaseStatus) {

        Map<String, Object> mappingInf = empIncomeServer.getTotalInf(empID, estabCaseStatus);

        return ResponseEntity.ok(mappingInf);
    }

    @GetMapping("/byMonth/{year}/{month}")
    public List<EstabCase> getEstabCasesByMonth(@PathVariable int year, @PathVariable int month) {
        return empIncomeServer.getEstabCasesByMonth(year, month);
    }
}

