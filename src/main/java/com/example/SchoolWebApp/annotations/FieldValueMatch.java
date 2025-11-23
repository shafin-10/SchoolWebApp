package com.example.SchoolWebApp.annotations;

import com.example.SchoolWebApp.validations.FieldValueMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldValueMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValueMatch {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldValueMatch[] value();
    }
}