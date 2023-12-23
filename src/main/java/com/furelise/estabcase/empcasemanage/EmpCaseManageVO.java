package com.furelise.estabcase.empcasemanage;

import com.furelise.emp.model.Emp;
import com.furelise.estabcase.model.EstabCase;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class EmpCaseManageVO {
    private Integer estabCaseID;//案件編號
    private Date estabCaseDate; // 收取時間 estabCase => estabCaseDate + pickupTime => timeRange
    private String timeRange;//收取區間
    private BigDecimal planPricePerCase;
    private String cityName;
    private Boolean takeStatus;
    private Integer estabCaseStatus;// 案件狀況 estabCase => estabCaseStatus 狀態:0(進行中)，2(逾時) 在進行中案件。1(完成收取)在下面
//
//    private String cityName;//郵遞區號+地區
//    // 案件資訊 planOrd => 抓以下資料
//    // 案件編號 收取日期 收取區間 收取地址 收區方式 垃圾量

//    private BigDecimal planPricePerCase;//方案收入

//    private String cityCode;
//    private String floor;
//    private Integer empID;
//    private List<EstabCase> estabCaseList;

    public EmpCaseManageVO() {
    }

    public EmpCaseManageVO(Integer estabCaseID, Date estabCaseDate, String timeRange, BigDecimal planPricePerCase, String cityName, Boolean takeStatus, Integer estabCaseStatus) {
        this.estabCaseID = estabCaseID;
        this.estabCaseDate = estabCaseDate;
        this.timeRange = timeRange;
        this.planPricePerCase = planPricePerCase;
        this.cityName = cityName;
        this.takeStatus = takeStatus;
        this.estabCaseStatus = estabCaseStatus;
    }
}
