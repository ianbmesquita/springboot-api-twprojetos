package br.com.treinaweb.twprojetos.api.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorValidation extends ApiError {
    private List<FieldError> errors;

    public ApiErrorValidation() {

    }

    public ApiErrorValidation(int erro, String status, LocalDateTime timestamp, String mensagem,
            List<FieldError> errors) {
        super(erro, status, timestamp, mensagem);
        this.errors = errors;
    }

    public ApiErrorValidation(List<FieldError> errors) {
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
