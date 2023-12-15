package com.furelise.mem.model.vo;

import com.furelise.mem.model.entity.Mem;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class MemVO {

    private Integer memID;
    private String memName;
    private String memMail;
    private String memTel;
    private Date memBirth;
    private Timestamp memRegiDate;
    private Timestamp memLastModified;

    public MemVO() {
    }

    public MemVO(Mem mem) {
        this.memID = mem.getMemID();
        this.memName = mem.getMemName();
        this.memMail = mem.getMemMail();
        this.memTel = mem.getMemTel();
        this.memBirth = mem.getMemBirth();
        this.memRegiDate = mem.getMemRegiDate();
        this.memLastModified = mem.getMemLastModified();
    }
}
