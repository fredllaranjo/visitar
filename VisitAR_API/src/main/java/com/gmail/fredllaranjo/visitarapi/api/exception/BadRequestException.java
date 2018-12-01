package com.gmail.fredllaranjo.visitarapi.api.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to be thrown when the request is invalid or improperly formed.
 * 
 * @author fredllaranjo
 */
public class BadRequestException extends HttpStatusCodeException {

    private static final long serialVersionUID = -8594009897752781628L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getHttpStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
