package com.furelise.admin.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AdminLoginDTO implements Serializable {

	@NotEmpty(message = "The account is not empty")
    @NotBlank(message = "The account is not blank")
	private String account;
	
	@NotEmpty(message = "The password is not empty")
    @NotBlank(message = "The password is not blank")
	private String password;
	
	public AdminLoginDTO() {
	}
	
}
