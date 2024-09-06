package com.example.teambbackend.util.annotation.noSpace;

import com.example.teambbackend.util.annotation.noSpace.NoSpaces;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoSpacesValidator implements ConstraintValidator<NoSpaces, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.contains(" ");
    }
}
