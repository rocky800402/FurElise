package com.furelise.ord.controller;

import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.service.AuthService;
import com.furelise.ord.model.*;
import com.furelise.orddetail.model.OrdDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/mem-ord")
public class MemOrdController {

    @Autowired
    private MemOrdService memOrdService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<MemOrdVO> getMemOrdVO(HttpServletRequest req){
        Mem mem = (Mem) authService.validate(req, "mem");
        return memOrdService.getMemOrdList(mem.getMemID());
    }
    @PatchMapping
    public OrdDetail updateMemOrdDetail(@Validated @RequestBody MemOrdLevelDTO memOrdLevelDTO){

        return memOrdService.updateMemOrdDetail(memOrdLevelDTO);
    }

    @PatchMapping("/status")
    public Ord updateMemOrdStatusDTO(@Validated @RequestBody MemOrdStatusDTO memOrdStatusDTO){

        return memOrdService.updateMemOrdStatusDTO(memOrdStatusDTO);
    }
}
