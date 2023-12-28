package com.furelise.estabcase.model;

import com.furelise.complaint.model.Complaint;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
@Data
public class BackendEstabCaseDetailVO {

    private String empName;
    private Integer estabCaseID;
    private Date estabCaseDate;
    private String timeRange;
    private String contact;
    private String contactTel;
    private String cityName;
    private String cityCode;
    private String floor;
    private String wayName;
    private Integer estabCaseStatus;
    private BigDecimal planPricePerCase;
    private List<Complaint> complaints;
    private Timestamp estabCaseEnd;
    private byte[] estabCasePic;
    private Boolean takeStatus;

    public BackendEstabCaseDetailVO(){}

    public BackendEstabCaseDetailVO(String empName, Integer estabCaseID, Date estabCaseDate, String timeRange, String contact, String contactTel, String cityName, String cityCode, String floor, String wayName, Integer estabCaseStatus, BigDecimal planPricePerCase, List<Complaint> complaints, Timestamp estabCaseEnd, byte[] estabCasePic, Boolean takeStatus) {
        this.empName = empName;
        this.estabCaseID = estabCaseID;
        this.estabCaseDate = estabCaseDate;
        this.timeRange = timeRange;
        this.contact = contact;
        this.contactTel = contactTel;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.floor = floor;
        this.wayName = wayName;
        this.estabCaseStatus = estabCaseStatus;
        this.planPricePerCase = planPricePerCase;
        this.complaints = complaints;
        this.estabCaseEnd = estabCaseEnd;
        this.estabCasePic = estabCasePic;
        this.takeStatus = takeStatus;
    }
}
