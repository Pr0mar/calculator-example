package marcin.lenda.calculator.example.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class CalculatorValidatorImpl implements ConstraintValidator<CalculatorValidator, String> {
    @Override
    public void initialize(CalculatorValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {

        if (Objects.isNull(value) || value.isEmpty()) {
            return false;
        }
        String[] values;
        try {
            values = value.split(" ");
        } catch (final PatternSyntaxException patternSyntaxException) {
            return false;
        }

        for (final String v : values) {
            boolean isNumber = isNumber(v);
            boolean isOperator = isOperator(v);

            if(!isNumber && !isOperator){
                return false;
            }
        }

        return true;
    }

    private boolean isOperator(final String v) {
        if (v.length() > 1) {
            return false;
        }

        switch (v.charAt(0)) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
                return true;
            default:
                return false;
        }
    }

    private boolean isNumber(final String v) {
        for (int i = 0; i < v.length(); i++) {
            if(isNegativeNumber(v, i)){
                continue;
            }
            if (!Character.isDigit(v.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean isNegativeNumber(String v, int i) {
        return v.length() > 1 && v.charAt(i) == '-' && i == 0;
    }
}
