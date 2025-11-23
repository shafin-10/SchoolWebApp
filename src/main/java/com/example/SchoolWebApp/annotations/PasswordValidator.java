package com.example.SchoolWebApp.annotations;

import com.example.SchoolWebApp.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    Class<?>[] groups() default {};
    String message() default "Please choose a stronger password";
    Class<? extends Payload>[] payload() default {};
}
