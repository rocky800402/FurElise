package com.furelise.estabcase.controller;

import com.furelise.estabcase.model.BackendEstabCaseService;
import com.furelise.estabcase.model.BackendEstabCaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/backend-estab-case")
public class BackendEstabCaseController {
    @Autowired
    private BackendEstabCaseService backendEstabCaseService;
    @GetMapping
    public List<BackendEstabCaseVO> getBackendEstabCaseTake(
             @RequestParam Boolean takeStatus,
             @RequestParam(required = false ,defaultValue = "1") int page,
             @RequestParam(required = false ,defaultValue = "5") int limit){
        return backendEstabCaseService.getBackendEstabCaseTake(page,limit,takeStatus);
    }
}
