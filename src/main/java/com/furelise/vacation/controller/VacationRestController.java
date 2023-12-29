package com.furelise.vacation.controller;

import com.furelise.emp.model.Emp;
import com.furelise.mem.service.AuthService;
import com.furelise.vacation.model.dto.VacationDTO;
import com.furelise.vacation.model.entity.Vacation;
import com.furelise.vacation.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/api/vacation")
public class VacationRestController {

    @Autowired
    private VacationService vaSvc;
    @Autowired
    private AuthService authService;

    @PostMapping
    public Vacation add(@RequestBody @Validated VacationDTO dto, HttpServletRequest req) {
        Emp emp = (Emp) authService.validate(req, "emp");
        // 調用 Service 的add方法
        return vaSvc.addVacation(emp.getEmpID(), dto.getVaStart(), dto.getVaEnd());
    }

    @GetMapping
    public List<Vacation> getEmpVacation(HttpServletRequest req) {
        Emp emp = (Emp) authService.validate(req, "emp");
        return vaSvc.getAllVacationDesc(emp.getEmpID());
    }

}
