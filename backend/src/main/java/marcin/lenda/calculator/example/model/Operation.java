package marcin.lenda.calculator.example.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String input;
    private String result;
}
