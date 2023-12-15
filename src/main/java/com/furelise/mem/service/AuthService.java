package com.furelise.mem.service;

import com.furelise.exception.UnauthorizedException;
import com.furelise.mem.model.dto.LoginDTO;
import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.repository.MemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private MemRepository memRepository;

    public Mem findByMemMail(String memMail) {
        return this.memRepository.findByMemMail(memMail);
    }

    public Mem verify(LoginDTO dto) throws UnauthorizedException {
        Mem mem = this.findByMemMail(dto.getEmail());
        // 判斷是不是沒有撈到成員，或者是已經被停權了，又或者是密碼不同，往外拋 401 的 exception
        if (mem == null || mem.getMemIsSuspended()|| !dto.getPassword().equals(mem.getMemPass())){
            throw new UnauthorizedException("The account or password is incorrect");
        }
        return mem;
    }

}
