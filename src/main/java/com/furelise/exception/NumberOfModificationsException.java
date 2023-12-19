package com.furelise.exception;

public class NumberOfModificationsException extends RuntimeException{

    public  NumberOfModificationsException(String massage){
        super(massage);
    }

    public NumberOfModificationsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberOfModificationsException(Throwable cause) {
        super(cause);
    }

    public NumberOfModificationsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NumberOfModificationsException() {
    }
}
