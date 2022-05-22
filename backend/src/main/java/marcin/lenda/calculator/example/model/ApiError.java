package marcin.lenda.calculator.example.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}
