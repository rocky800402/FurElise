package com.furelise.ord.controller;

import com.furelise.mem.model.entity.Mem;
import com.furelise.ord.model.MemOrdService;
import com.furelise.ord.model.MemOrdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/mem-ord")
public class MemOrdController {
    @Autowired
    private MemOrdService memOrdService;

    @GetMapping
    public List<MemOrdVO> getMemOrdVO( HttpServletRequest req){
        Mem mem = (Mem) req.getSession().getAttribute("mem");
        return memOrdService.getMemOrdVO(mem.getMemID());
    }
}
