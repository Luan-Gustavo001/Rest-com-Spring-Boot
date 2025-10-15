package com.gustavo.luan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {

    public RequiredObjectIsNullException() {
        super("Objeto não pode ser nulo");
    }

    public RequiredObjectIsNullException(String message) {
        super(message);
    }

}
