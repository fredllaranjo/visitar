
package com.gmail.fredllaranjo.visitarapi.api.exception;

public abstract class HttpStatusCodeException extends Exception {

    private static final long serialVersionUID = 4883252350403356613L;

    public HttpStatusCodeException(String message) {
        super(message);
    }

    public HttpStatusCodeException(Throwable cause) {
        super(cause);
    }

    public HttpStatusCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getHttpStatusCode();
}
