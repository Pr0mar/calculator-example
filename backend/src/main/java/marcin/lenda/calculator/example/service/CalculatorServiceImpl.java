package marcin.lenda.calculator.example.service;

import marcin.lenda.calculator.example.exception.CalculatorException;
import marcin.lenda.calculator.example.exception.CustomArithmeticException;
import marcin.lenda.calculator.example.model.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private final static List<Character> OPERATORS = Arrays.asList('+', '-', '*', '/');
    private final static Map<Character, Integer> OPERATOR_WEIGHT = new HashMap<>();
    private Stack<Character> operators;
    private Stack<Double> values;
    private String globalInput;

    @Autowired
    public CalculatorServiceImpl() {
        this.operators = new Stack<>();
        this.values = new Stack<>();
        initialOperatorWeight();
    }

    private void initialOperatorWeight() {
        OPERATOR_WEIGHT.put('+', 1);
        OPERATOR_WEIGHT.put('-', 1);
        OPERATOR_WEIGHT.put('*', 2);
        OPERATOR_WEIGHT.put('/', 2);
    }

    public String calculateInput(@Valid final Input input) {
        final String[] tokens = input.getValue().split(" ");
        this.globalInput = input.getValue();
        double result = 0;
        processAllInput(tokens);
        checkIfProcessCanStart();
        result = values.peek();
        values.pop();

        if (!operators.empty() || !values.empty()) {
            throw new CalculatorException(input.getValue());
        }

        return String.valueOf(result);
    }

    public void processAllInput(final String[] tokens) {

        for (int n = 0; n < tokens.length; n++) {
            final String nextToken = tokens[n];
            final char character = nextToken.charAt(0);

            if (addValueIfIsNumber(nextToken, character, values)) {
                continue;
            }

            findOperatorAndProcessedIfNeeded(character);
            checkIfItIsParenthesisAndProcess(character);
        }
    }

    private void checkIfItIsParenthesisAndProcess(char character) {
        if (character == '(') {
            operators.push(character);
        } else if (character == ')') {
            checkIfProcessCanStart();
            checkIfNextCharacterIsParenthesis();
        }
    }

    private void checkIfNextCharacterIsParenthesis() {
        if (!operators.empty() && operators.peek() == '(') {
            operators.pop();
        } else {
            throw new CalculatorException("Unbalanced parenthesis. Input: %s", globalInput);
        }
    }

    private void checkIfProcessCanStart() {
        while (!operators.empty() && OPERATORS.contains(operators.peek())) {
            char toProcess = operators.peek();
            operators.pop();
            processOperator(toProcess);
        }
    }

    private void findOperatorAndProcessedIfNeeded(final char character) {
        if (OPERATORS.contains(character)) {
            if (operators.empty() || getPrecedence(character) > getPrecedence(operators.peek())) {
                operators.push(character);
            } else {
                while (!operators.empty() && getPrecedence(character) <= getPrecedence(operators.peek())) {
                    char toProcess = operators.peek();
                    operators.pop();
                    processOperator(toProcess);
                }
                operators.push(character);
            }
        }
    }

    private int getPrecedence(final char operator) {
        final Integer weight = OPERATOR_WEIGHT.get(operator);
        return Objects.nonNull(weight)
                ? weight
                : 0;
    }

    private void processOperator(final char operator) {
        final Optional<Double> maybeNumber2 = getValue();
        final Optional<Double> maybeNumber1 = getValue();

        if (!maybeNumber1.isPresent() || !maybeNumber2.isPresent()) {
            return;
        }

        final double number1 = maybeNumber1.get();
        final double number2 = maybeNumber2.get();

        double result = 0.0;

        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                if (number1 == 0 || number2 == 0) {
                    throw new CustomArithmeticException(number1, number2);
                }
                result = number1 / number2;
                break;
            default:
                throw new CalculatorException("Operator error. Input: %s", globalInput);
        }
        values.push(result);
    }

    private Optional<Double> getValue() {
        if (values.empty()) {
            throw new CalculatorException("Expression error.. Input: %s", globalInput);
        } else {
            final Double value = values.peek();
            values.pop();

            return Optional.of(value);
        }
    }

    private boolean addValueIfIsNumber(final String nextToken, final char character, final Stack<Double> values) {
        try {
            Double.parseDouble(String.valueOf(nextToken));
            double value = Double.parseDouble(nextToken);
            values.push(value);
            return true;
        } catch (final NumberFormatException numberFormatException) {
            return false;
        }
    }
}
