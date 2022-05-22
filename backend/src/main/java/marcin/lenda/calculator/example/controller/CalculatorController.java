package marcin.lenda.calculator.example.controller;

import marcin.lenda.calculator.example.model.Input;
import marcin.lenda.calculator.example.model.Operation;
import marcin.lenda.calculator.example.repository.OperationRepository;
import marcin.lenda.calculator.example.service.CalculatorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/calculator")
@CrossOrigin(origins =  {"${app.dev.frontend.local}"})
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final OperationRepository repository;

    public CalculatorController(CalculatorService calculatorService, OperationRepository repository) {
        this.calculatorService = calculatorService;
        this.repository = repository;
    }

    @PostMapping
    public String calculate(@RequestBody @Valid final Input input) {
        final String result = calculatorService.calculateInput(input);
        final Operation r = Operation.builder().input(input.getValue()).result(result).build();
        repository.save(r);

        return result;
    }

    @GetMapping
    public List<Operation> getOperations() {
        return repository.findAll();
    }
}
