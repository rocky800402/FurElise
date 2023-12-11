package com.furelise.estabcase.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
public class MemEstabCaseVO {

    //PlanOrd方案
    private Integer planOrdID;
//    private Integer planID;
    private String planName;
//    private Integer timeID;
    private String timeRange;
//    private Integer periodID;
    private Integer planPeriod;
    private String day;
    private String dayCode;
//    private Integer wayID;
    private String wayName;
    private Integer memID;
    private String memName;
    private Date planStart;
    private Date planEnd;
    private BigDecimal total;
    private String cityName;
    private String cityCode;
    private String floor;
    private String contact;
    private String contactTel;


    //EstabCase案件
//    private Integer estabCaseID;
//    private Date estabCaseDate;
//    private Timestamp estabCaseStart;
//    private Integer estabCaseStatus;
//    private Integer estabCaseLevel;
//    private String estabCaseFeedback;
//    private Timestamp estabCaseFBTime;
    private List<EstabCase> estabCaseList;


   public  MemEstabCaseVO(){}

    public MemEstabCaseVO(Integer planOrdID, String planName, String timeRange, Integer planPeriod, String day, String dayCode, String wayName, Date planStart, Date planEnd, BigDecimal total, String cityName, String cityCode, String floor, String contact, String contactTel, List<EstabCase> estabCaseList) {
        this.planOrdID = planOrdID;
        this.planName = planName;
        this.timeRange = timeRange;
        this.planPeriod = planPeriod;
        this.day = day;
        this.dayCode = dayCode;
        this.wayName = wayName;
        this.planStart = planStart;
        this.planEnd = planEnd;
        this.total = total;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.floor = floor;
        this.contact = contact;
        this.contactTel = contactTel;
        this.estabCaseList = estabCaseList;
    }
}
