package com.gmail.fredllaranjo.visitarapi.api.vm;

import java.io.Serializable;

/**
 * REST API Exception representation
 * 
 * @author fredllaranjo
 */
public class ExceptionVm implements Serializable {

    private static final long serialVersionUID = -6282790342958501764L;

    private int status;
    private String message;
    private String name;

    public ExceptionVm(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMesssage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExceptionVm [status=" + status + ", message=" + message + ", name=" + name + "]";
    }

}
