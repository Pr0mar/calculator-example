package marcin.lenda.calculator.example.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CalculatorValidatorImpl.class)
@Documented
public @interface CalculatorValidator {

    String message() default "Invalid value.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
