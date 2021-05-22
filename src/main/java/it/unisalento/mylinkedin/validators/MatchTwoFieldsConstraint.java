package it.unisalento.mylinkedin.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchTwoFieldsValidator.class)
public @interface MatchTwoFieldsConstraint {

    String field();
    String fieldMatch();
    String message() default "The fields must be equal";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}