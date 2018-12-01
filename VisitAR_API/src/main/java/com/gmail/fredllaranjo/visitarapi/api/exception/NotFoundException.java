package com.gmail.fredllaranjo.visitarapi.api.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpStatusCodeException {

    private static final long serialVersionUID = -178396027521114141L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getHttpStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
