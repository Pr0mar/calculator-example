package marcin.lenda.calculator.example.model;

import lombok.*;
import marcin.lenda.calculator.example.validator.CalculatorValidator;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Input {

    @CalculatorValidator(message = "Invalid value.")
    private String value;
}
