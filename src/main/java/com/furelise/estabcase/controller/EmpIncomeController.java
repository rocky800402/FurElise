package com.furelise.estabcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/incomepage")
public class EmpIncomeController {

    @GetMapping("/")
    public String backEndEstabcase(){
        return "jack_income";
    }
}
