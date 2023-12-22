package com.furelise.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ByteArrayValidator implements ConstraintValidator<ValidByteArray, byte[]> {
    @Override
    public void initialize(ValidByteArray constraintAnnotation) {
    }

    @Override
    public boolean isValid(byte[] value, ConstraintValidatorContext context) {
        return value != null && value.length > 0;
    }
}

