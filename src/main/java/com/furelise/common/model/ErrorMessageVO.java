package com.furelise.common.model;

import lombok.Data;

@Data
public class ErrorMessageVO {

    private String message;

    public ErrorMessageVO() {}

    public ErrorMessageVO(String message) {
        this.message = message;
    }
}
