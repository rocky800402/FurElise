package com.furelise.mem.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {

    @Email(message = "The email format is wrong")
    private String email;

    @NotEmpty(message = "The password is not empty")
    @NotBlank(message = "The password is not blank")
    private String password;

    public LoginDTO() {
    }
}
