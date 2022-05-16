package com.s2u2m.services.core.error;

import com.s2u2m.services.core.error.exception.ClientValidationException;
import com.s2u2m.services.core.error.exception.InternalException;
import com.s2u2m.services.core.error.exception.RemoteServiceException;
import com.s2u2m.services.core.error.exception.RepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ConditionalOnMissingBean(annotation = {ControllerAdvice.class})
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientValidationException.class)
    public ResponseEntity<ErrorResponseBody> handleClientValidationException(ClientValidationException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseBody(ex.getInfo().getCode(), ex.getMessage()));
    }

    @ExceptionHandler(RemoteServiceException.class)
    public ResponseEntity<ErrorResponseBody> handleRemoteServiceException(RemoteServiceException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorResponseBody(ex.getInfo().getCode(), ex.getMessage()));
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<ErrorResponseBody> handleRepositoryException(RepositoryException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorResponseBody(ex.getInfo().getCode(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseBody> handleInternalException(InternalException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.internalServerError()
                .body(new ErrorResponseBody(ex.getInfo().getCode(), ex.getMessage()));
    }
}
