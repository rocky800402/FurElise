package com.furelise.estabcase.controller;

import com.furelise.estabcase.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/estabcase")
public class EstabCaseController {

    @Autowired
    private DispatchService dispatchService;

    @GetMapping("/get-date")
    public void getDate() {
        dispatchService.assignWorkToEmp();
    }

}
