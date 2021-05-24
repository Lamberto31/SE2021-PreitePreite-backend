package it.unisalento.mylinkedin.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckValueInListValidator.class)
public @interface CheckValueInListConstraint {

    String[] feasibleList();

    String message() default "Value not in feasible list";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
