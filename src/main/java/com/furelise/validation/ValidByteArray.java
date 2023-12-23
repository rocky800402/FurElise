package com.furelise.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ByteArrayValidator.class)
public @interface ValidByteArray {
    String message() default "this is default message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

