package com.s2u2m.services.core.error.exception;

import com.s2u2m.services.core.error.ExceptionDetails;

public class RepositoryException extends BaseException {
    public RepositoryException(ExceptionDetails info, Object... args) {
        super(info, args);
    }

    public RepositoryException(ExceptionDetails info, Throwable cause, Object... args) {
        super(info, cause, args);
    }
}
