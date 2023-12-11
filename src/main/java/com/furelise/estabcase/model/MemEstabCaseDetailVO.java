package com.furelise.estabcase.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
@Data
public class MemEstabCaseDetailVO {
    private Integer estabCaseID;
    private Integer empID;
    private String empName;
    private Integer planOrdID;
    private Date estabCaseDate;
    private Timestamp estabCaseStart;
    private Timestamp estabCaseEnd;
    private BigDecimal planPricePerCase;
    private byte[] estabCasePic;
    private boolean takeStatus;
    private Integer estabCaseStatus;
    private Integer estabCaseLevel;
    private String estabCaseFeedback;
    private Timestamp estabCaseFBTime;

    public MemEstabCaseDetailVO(){}

    public MemEstabCaseDetailVO(Integer estabCaseID, Integer empID, String empName, Integer planOrdID, Date estabCaseDate, Timestamp estabCaseStart, Timestamp estabCaseEnd, BigDecimal planPricePerCase, byte[] estabCasePic, boolean takeStatus, Integer estabCaseStatus, Integer estabCaseLevel, String estabCaseFeedback, Timestamp estabCaseFBTime) {
        this.estabCaseID = estabCaseID;
        this.empID = empID;
        this.empName = empName;
        this.planOrdID = planOrdID;
        this.estabCaseDate = estabCaseDate;
        this.estabCaseStart = estabCaseStart;
        this.estabCaseEnd = estabCaseEnd;
        this.planPricePerCase = planPricePerCase;
        this.estabCasePic = estabCasePic;
        this.takeStatus = takeStatus;
        this.estabCaseStatus = estabCaseStatus;
        this.estabCaseLevel = estabCaseLevel;
        this.estabCaseFeedback = estabCaseFeedback;
        this.estabCaseFBTime = estabCaseFBTime;
    }
}
