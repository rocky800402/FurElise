package com.furelise.estabcase.controller;

import com.furelise.estabcase.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/status")
    public List<BackendEstabCaseVO> getBackendEstabCaseStatus(
            @RequestParam Boolean takeStatus,
            @RequestParam(required = false ,defaultValue = "1") int page,
            @RequestParam(required = false ,defaultValue = "5") int limit){
        return backendEstabCaseService.getBackendEstabCaseStatus(page,limit,takeStatus);
    }@GetMapping("/dispatch")
    public List<BackendEstabCaseVO> getBackendEstabCaseDispatch(
            @RequestParam(required = false ,defaultValue = "1") int page,
            @RequestParam(required = false ,defaultValue = "5") int limit){
        return backendEstabCaseService.getBackendEstabCaseDispatch(page,limit,true);
    }
    @GetMapping("/Detail/{estabCaseID}")
    public BackendEstabCaseDetailVO getBackendEstabCaseDetail(
            @PathVariable Integer estabCaseID){
        return backendEstabCaseService.getBackendEstabCaseDetail(estabCaseID);
    }
    @PatchMapping
    public EstabCase setEstabCaseReassign(
            @Validated @RequestBody BackendEstabCaseReassignDTO backendEstabCaseReassignDTO){
        return backendEstabCaseService.setEstabCaseReassign(backendEstabCaseReassignDTO);
    }
}

