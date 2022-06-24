package com.s2u2m.services.core.error.exception;

import com.s2u2m.services.core.error.ErrorCode;

public class RepositoryException extends BaseException {
    public RepositoryException(ErrorCode info, String msgWithPattern, Object... args) {
        super(info, msgWithPattern, args);
    }

    public RepositoryException(ErrorCode info, Throwable cause, String msgWithPattern, Object... args) {
        super(info, cause, msgWithPattern, args);
    }
}
