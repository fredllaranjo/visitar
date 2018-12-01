package com.gmail.fredllaranjo.visitarapi.api.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to be thrown when some precondition is failed, 
 * e.g. there is no directory to save the file.
 * @author fredllaranjo
 */
public class PreconditionFailedException extends HttpStatusCodeException {

    private static final long serialVersionUID = -8594009897752781628L;

    public PreconditionFailedException(String message) {
        super(message);
    }

    public PreconditionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getHttpStatusCode() {
        return HttpStatus.PRECONDITION_FAILED.value();
    }
}
