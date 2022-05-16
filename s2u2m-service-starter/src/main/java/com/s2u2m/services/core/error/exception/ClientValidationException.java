package com.s2u2m.services.core.error.exception;

import com.s2u2m.services.core.error.ExceptionDetails;

public class ClientValidationException extends BaseException {
    public ClientValidationException(ExceptionDetails info, Object... args) {
        super(info, args);
    }

    public ClientValidationException(ExceptionDetails info, Throwable cause, Object... args) {
        super(info, cause, args);
    }
}
