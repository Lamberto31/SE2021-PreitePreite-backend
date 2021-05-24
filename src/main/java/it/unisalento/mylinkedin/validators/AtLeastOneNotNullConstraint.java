package it.unisalento.mylinkedin.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneNotNullValidator.class)
public @interface AtLeastOneNotNullConstraint {

    String[] fields();
    String message() default "At least one must be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
