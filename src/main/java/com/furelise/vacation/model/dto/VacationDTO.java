package com.furelise.vacation.model.dto;

import java.sql.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class VacationDTO {
	
	@Future(message = "The vacation should start after today")
	@NotNull(message = "The start date shouldn't be null")
	private Date vaStart;
	@Future(message = "The vacation should end after today")
	@NotNull(message = "The start date shouldn't be null")
	private Date vaEnd;
	
	public VacationDTO() {
	}
}
