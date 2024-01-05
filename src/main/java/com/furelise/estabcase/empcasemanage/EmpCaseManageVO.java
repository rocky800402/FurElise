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


    public EmpCaseManageVO() {
    }
}
