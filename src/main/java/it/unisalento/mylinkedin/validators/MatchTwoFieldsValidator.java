package it.unisalento.mylinkedin.validators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchTwoFieldsValidator implements ConstraintValidator<MatchTwoFieldsConstraint, Object> {

    String field;
    String fieldMatch;

    @Override
    public void initialize(MatchTwoFieldsConstraint constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        assert fieldValue != null;
        return fieldValue.equals(fieldMatchValue);
    }
}