package com.furelise.estabcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/incomepage")
public class EmpIncomeController {

    @GetMapping("/")
    public String backEndEstabcase(HttpSession session){
        return "jack_income";
    }
}
