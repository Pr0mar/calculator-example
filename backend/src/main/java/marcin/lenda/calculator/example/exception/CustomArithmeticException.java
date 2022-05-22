package marcin.lenda.calculator.example.exception;

public class CustomArithmeticException extends RuntimeException {

    public CustomArithmeticException(final double number1, final double number2) {
        super(String.format("Cannot  dividing by 0: %s / %s", number1, number2));
    }
}
