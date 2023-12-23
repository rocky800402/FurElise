package com.furelise.estabcase.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class BackendEstabCaseVO {
    private Integer estabCaseID;
    private Date estabCaseDate;
    private String timeRange;
    private String cityName;
    private Integer empID;
    private Boolean takeStatus;
    private Boolean dispatchStatus;
    private Integer estabCaseStatus;
    private BigDecimal planPricePerCase;
}
