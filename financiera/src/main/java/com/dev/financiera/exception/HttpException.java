package com.dev.financiera.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {
    private final HttpStatus status;

    public HttpException(HttpStatus status, String mensaje) {
        super(mensaje);
        this.status = status;

    }
}
