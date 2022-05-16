package com.s2u2m.services.core.error.exception;

import com.s2u2m.services.core.error.ExceptionDetails;

public class InternalException extends BaseException {
    public InternalException(ExceptionDetails info, Object... args) {
        super(info, args);
    }

    public InternalException(ExceptionDetails info, Throwable cause, Object... args) {
        super(info, cause, args);
    }
}
