package com.furelise.emp.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class EmpLoginDTO implements Serializable {

	@Email(message = "The email format is wrong")
	private String email;
	
	@NotEmpty(message = "The password is not empty")
    @NotBlank(message = "The password is not blank")
	private String password;
	
	public EmpLoginDTO() {
	}
}
