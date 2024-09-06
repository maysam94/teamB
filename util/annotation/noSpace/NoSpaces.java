package com.example.teambbackend.util.annotation.noSpace;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoSpacesValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSpaces {
    String message() default "The field cannot contain spaces";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
