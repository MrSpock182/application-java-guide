package io.github.mrspock182.rest.application.resource.advice;

import io.github.mrspock182.rest.application.resource.dto.ErrorResponse;
import io.github.mrspock182.rest.domain.exception.BadRequest;
import io.github.mrspock182.rest.domain.exception.InternalServerError;
import io.github.mrspock182.rest.domain.exception.NotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class HandlerExceptionAdvice {
    private static final Logger log = LogManager.getLogger(HandlerExceptionAdvice.class);

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, InternalServerError.class})
    public ErrorResponse handleErrorNotMap(
            final Exception exception,
            final HttpServletRequest request
    ) {
        String error = "Request: " + request + " Error: " + exception;
        log.error(error);
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({NotFound.class})
    public ErrorResponse handleNotFound(
            final NotFound exception,
            final HttpServletRequest request
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                exception.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception,
            final HttpServletRequest request
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                exception.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({BadRequest.class})
    public ErrorResponse handleBadRequest(
            final BadRequest exception,
            final HttpServletRequest request
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                exception.getMessage()
        );
    }
}
