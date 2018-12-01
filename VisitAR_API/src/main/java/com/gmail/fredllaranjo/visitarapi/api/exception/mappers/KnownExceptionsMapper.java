package com.gmail.fredllaranjo.visitarapi.api.exception.mappers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gmail.fredllaranjo.visitarapi.api.exception.BadRequestException;
import com.gmail.fredllaranjo.visitarapi.api.exception.NotFoundException;
import com.gmail.fredllaranjo.visitarapi.api.exception.PreconditionFailedException;
import com.gmail.fredllaranjo.visitarapi.api.vm.ExceptionVm;

/**
 * Mapper for known exceptions to statuses codes
 * 
 * @author fredllaranjo
 */
@ControllerAdvice
public class KnownExceptionsMapper {

    /**
     * Maps the EntityNotFoundException to 404 NOT FOUND status code
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ExceptionVm mapEntityNotFoundException(NotFoundException e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }

    /**
     * Maps the PreconditionFailedException to 412 PRECONDITION FAILED status
     * code
     */
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(PreconditionFailedException.class)
    @ResponseBody
    public ExceptionVm mapPreconditionFailedException(PreconditionFailedException e) {
        return handleException(e, HttpStatus.PRECONDITION_FAILED);
    }

    /**
     * Maps the BadRequestException to 400 BAD REQUEST status code
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ExceptionVm mapBadRequestException(BadRequestException e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    private ExceptionVm handleException(Exception e, HttpStatus httpStatus) {
        return new ExceptionVm(httpStatus.value(), e.getMessage().replace("\n", " "));
    }

}
