package com.s2u2m.services.core.error.exception;

import com.s2u2m.services.core.error.ErrorCode;

public class ClientValidationException extends BaseException {
    public ClientValidationException(ErrorCode info, String msgWithPattern, Object... args) {
        super(info, msgWithPattern, args);
    }

    public ClientValidationException(ErrorCode info, Throwable cause, String msgWithPattern, Object... args) {
        super(info, cause, msgWithPattern, args);
    }
}
