package com.kodilla.kodillabytereflecion;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<Range, Integer> {
    private int min;
    private int max;

    @Override
    public void initialize(Range range) {
        this.min = range.min();
        this.max = range.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value >= min && value <= max;
    }
}
