package com.thiennbao.kouhii.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumNameValidator implements ConstraintValidator<EnumNameValid, String> {
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumNameValid constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> e.name().equals(value));
    }
}
