package com.s2u2m.services.core.error.exception;

import com.s2u2m.services.core.error.ErrorCode;

public class InternalException extends BaseException {
    public InternalException(ErrorCode info, String msgWithPattern, Object... args) {
        super(info, msgWithPattern, args);
    }

    public InternalException(ErrorCode info, Throwable cause, String msgWithPattern, Object... args) {
        super(info, cause, msgWithPattern, args);
    }
}
