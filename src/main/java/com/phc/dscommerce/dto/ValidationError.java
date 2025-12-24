package com.phc.dscommerce.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ValidationError extends CustomError {
    private Set<FieldMessage> errors = new HashSet<>();

    public ValidationError(LocalDateTime timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public Set<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}