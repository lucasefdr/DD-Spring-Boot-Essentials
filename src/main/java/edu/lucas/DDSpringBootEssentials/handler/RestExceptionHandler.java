package edu.lucas.DDSpringBootEssentials.handler;

import edu.lucas.DDSpringBootEssentials.exception.BadRequestException;
import edu.lucas.DDSpringBootEssentials.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

// Dica para o controller -> todos os controllers precisam utilizar isso
@ControllerAdvice
public class RestExceptionHandler {
    // Toda vez que lançar uma exceção de "bad request" faça isso...
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad request excpetion, check the documentation.")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
