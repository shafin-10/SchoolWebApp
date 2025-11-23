package com.example.SchoolWebApp.validations;

import com.example.SchoolWebApp.annotations.FieldValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldValueMatchValidator
        implements ConstraintValidator<FieldValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldValueMatch constraintAnnotation) {
       field = constraintAnnotation.field();
       fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(object).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(object).getPropertyValue(fieldMatch);

        if(fieldValue != null){
            if(fieldValue.toString().startsWith("$2a")){
                return true;
            }
            else {
                return fieldValue.equals(fieldMatchValue);
            }
        }
        else{
            return fieldMatchValue == null;
        }
    }
}