package marcin.lenda.calculator.example.controller;

import marcin.lenda.calculator.example.exception.CalculatorException;
import marcin.lenda.calculator.example.exception.CustomArithmeticException;
import marcin.lenda.calculator.example.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> error = Arrays.asList("No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL());

        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        //
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), Collections.singletonList("error occurred"));
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ CustomArithmeticException.class })
    public ResponseEntity<Object> handleDividingByZero(final CustomArithmeticException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("bad request", ex);
        //
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), Collections.singletonList("error occurred"));
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ CalculatorException.class })
    public ResponseEntity<Object> handleCalculatorException(final CustomArithmeticException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("bad request", ex);

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), Collections.singletonList("error occurred"));
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
