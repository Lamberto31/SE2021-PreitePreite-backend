package it.unisalento.mylinkedin.validators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNullConstraint, Object> {

    String[] fields;

    @Override
    public void initialize(AtLeastOneNotNullConstraint constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        for (String string : fields) {
            Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(string);
            if (fieldValue != null && !fieldValue.toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}