package com.furelise.mem.controller;

import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.model.vo.MemVO;
import com.furelise.mem.service.AuthService;
import com.furelise.mem.model.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public MemVO login(@RequestBody @Validated LoginDTO dto, HttpServletRequest req) {
        Mem mem = this.authService.verify(dto);
        HttpSession session = req.getSession();
        session.setAttribute("account", mem);
        return new MemVO(mem);
    }


}
