package com.furelise.estabcase.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
public class BackendEstabCaseReassignDTO {
    private Integer estabCaseID;
    @NotNull(message = "不可為空")
    @Future(message = "日期必須是在今日(不含)之後")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate estabCaseDate;

}
