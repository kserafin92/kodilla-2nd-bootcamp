package com.kodilla.kodillabytereflecion;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RangeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
    int min();
    int max();

    String message() default "Wartość musi być pomiędzy {min} a {max}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
