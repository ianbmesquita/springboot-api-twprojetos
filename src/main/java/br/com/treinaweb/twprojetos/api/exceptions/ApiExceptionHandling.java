package br.com.treinaweb.twprojetos.api.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandling extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiError apiError = new ApiError(
            httpStatus.value(),
            httpStatus.getReasonPhrase(),
            LocalDateTime.now(),
            exception.getLocalizedMessage()
        );

        return new ResponseEntity<ApiError>(apiError, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.add(new FieldError(
                fieldError.getField(),
                fieldError.getDefaultMessage()
            ));
        });
        
        ApiErrorValidation apiErrorValidation = new ApiErrorValidation(
            status.value(),
            status.getReasonPhrase(),
            LocalDateTime.now(),
            "Houveram erros de validação",
            errors
        );

        return new ResponseEntity<>(apiErrorValidation, status);
    }
}
