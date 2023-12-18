package com.furelise.estabcase.model;

import com.furelise.complaint.model.Complaint;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Data
public class MemEstabCaseBO {
    private Integer estabCaseID;
    private Integer empID;
    private Integer planOrdID;
    private Date estabCaseDate;
    private Timestamp estabCaseStart;
    private Timestamp estabCaseEnd;
    private BigDecimal planPricePerCase;
    private byte[] estabCasePic;
    private Boolean takeStatus;
    private Integer estabCaseStatus;
    private Integer estabCaseLevel;
    private String estabCaseFeedback;
    private Timestamp estabCaseFBTime;

    private List<Complaint> complaints;


    private Integer complaintID;
    private Integer memID;

    private String comDetail;
    private String comTel;
    private boolean comStatus;
    private Timestamp comStart;
    private Timestamp comEnd;

    public MemEstabCaseBO() {
    }

    public MemEstabCaseBO(EstabCase estabCase, Complaint complaint) {
        this.estabCaseID=estabCase.getEstabCaseID();
        this.empID=estabCase.getEmpID();
        this.planOrdID=estabCase.getPlanOrdID();
        this.estabCaseDate=estabCase.getEstabCaseDate();
        this.estabCaseStart=estabCase.getEstabCaseStart();
        this.estabCaseEnd=estabCase.getEstabCaseEnd();
        this.planPricePerCase=estabCase.getPlanPricePerCase();
        this.estabCasePic=estabCase.getEstabCasePic();
        this.takeStatus=estabCase.getTakeStatus();
        this.estabCaseStatus=estabCase.getEstabCaseStatus();
        this.estabCaseLevel=estabCase.getEstabCaseLevel();
        this.estabCaseFeedback=estabCase.getEstabCaseFeedback();
        this.estabCaseFBTime=estabCase.getEstabCaseFBTime();
        if(!Objects.isNull(complaint)){
            this.complaintID=complaint.getComplaintID();
            this.memID=complaint.getMemID();
            this.comDetail=complaint.getComDetail();
            this.comTel=complaint.getComTel();
            this.comStatus=complaint.getComStatus();
            this.comStart=complaint.getComStart();
            this.comEnd=complaint.getComEnd();

        }
    }

}
