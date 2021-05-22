package it.unisalento.mylinkedin.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckValueInListValidator implements ConstraintValidator<CheckValueInListConstraint, String> {

    String[] feasibleList;

    @Override
    public void initialize(CheckValueInListConstraint constraintAnnotation) {
        this.feasibleList = constraintAnnotation.feasibleList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for( String string: feasibleList) {
            if (string.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
