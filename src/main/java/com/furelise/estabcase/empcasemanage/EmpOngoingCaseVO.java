package com.furelise.estabcase.empcasemanage;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class EmpOngoingCaseVO {

    private Integer estabCaseID;//案件編號 estabCase
    private Date estabCaseDate;// 收取日期 estabCase
    private BigDecimal planPricePerCase;//收入 estabCase
    private Boolean takeStatus;//接單狀態 estabCase
    private String timeRange;//收取區間 pickupTime
    private String cityCode;//郵遞區號 planOrd
    private String floor;//街道樓層 planOrd
    private String pickupStop;//擺放位置planOrd
    private String cityName;//地址 city
    private String wayName;//收取方式 pickupWay
    private Integer liter;//垃圾量 plan

//    private Integer estabCaseStatus;
    /*
    estabCase
    pickupTime
    planOrd
    city
    pickupWay
    plan
    */

    public EmpOngoingCaseVO() {
    }
}
