package marcin.lenda.calculator.example;

import marcin.lenda.calculator.example.model.Input;
import marcin.lenda.calculator.example.service.CalculatorServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorServiceImplTest {

    @Autowired
    private CalculatorServiceImpl calculatorService;

    @Test
    public void expression_ex1_should_return_2() {
        //given
        final Input expression = Input.builder().value("1 + 3 - 2").build();

        //when
        final String result = calculatorService.calculateInput(expression);

        //then
        Assertions.assertThat(result).isEqualTo("2.0");
    }

    @Test
    public void expression_ex2_should_return_7() {
        //given
        final Input expression = Input.builder().value("3 * 2 + 1").build();

        //when
        final String result = calculatorService.calculateInput(expression);

        //then
        Assertions.assertThat(result).isEqualTo("7.0");
    }

    @Test
    public void expression_ex3_should_return_0() {
        //given
        final Input expression = Input.builder().value("3 * -2 + 6").build();

        //when
        final String result = calculatorService.calculateInput(expression);

        //then
        Assertions.assertThat(result).isEqualTo("0.0");
    }

    @Test
    public void expression_ex4_should_return_14() {
        //given
        final Input expression = Input.builder().value("4 + 5 * 2").build();

        //when
        final String result = calculatorService.calculateInput(expression);

        //then
        Assertions.assertThat(result).isEqualTo("14.0");
    }

    @Test
    public void expression_ex5_should_return_18() {
        //given
        final Input expression = Input.builder().value("( 4 + 5 ) * 2").build();

        //when
        final String result = calculatorService.calculateInput(expression);

        //then
        Assertions.assertThat(result).isEqualTo("18.0");
    }

    @Test
    public void expression_ex6_should_return_28() {
        //given
        final Input expression = Input.builder().value("( 4 + 5 ) * 2 + ( 5 + 5 )").build();

        //when
        final String result = calculatorService.calculateInput(expression);

        //then
        Assertions.assertThat(result).isEqualTo("28.0");
    }
}
