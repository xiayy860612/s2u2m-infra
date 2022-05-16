package com.s2u2m.services.core.error.exception;

import com.s2u2m.services.core.error.ExceptionDetails;

public class RemoteServiceException extends BaseException {
    public RemoteServiceException(ExceptionDetails info, Object... args) {
        super(info, args);
    }

    public RemoteServiceException(ExceptionDetails info, Throwable cause, Object... args) {
        super(info, cause, args);
    }
}
