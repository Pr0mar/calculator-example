package marcin.lenda.calculator.example.exception;

public class CalculatorException extends RuntimeException {

    public CalculatorException(final String message, final String input) {
        super(String.format(message, input));
    }

    public CalculatorException(final String input) {
        super(String.format("CalculatorServiceImpl: Someting going wroing with input: %s", input));
    }
}
