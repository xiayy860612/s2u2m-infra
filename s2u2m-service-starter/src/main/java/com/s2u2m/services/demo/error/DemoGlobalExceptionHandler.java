package com.s2u2m.services.demo.error;

import com.s2u2m.services.core.error.ErrorResponseBody;
import com.s2u2m.services.core.error.GlobalExceptionHandler;
import com.s2u2m.services.core.error.exception.ClientValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class DemoGlobalExceptionHandler extends GlobalExceptionHandler {

    @Override
    public ResponseEntity<ErrorResponseBody> handleClientValidationException(ClientValidationException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseBody(ex.getCode().getValue(), this.getClass().getSimpleName() + ": " + ex.getMessage()));
    }
}
