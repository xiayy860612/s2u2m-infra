package com.s2u2m.services.core.error.exception;

import com.s2u2m.services.core.error.ErrorCode;
import lombok.Getter;

import java.text.MessageFormat;

public abstract class BaseException extends RuntimeException {

    @Getter
    private final transient ErrorCode code;

    protected BaseException(ErrorCode code, String msgWithPattern, Object... args) {
        this(code, null, msgWithPattern, args);
    }

    protected BaseException(ErrorCode code, Throwable cause, String msgWithPattern, Object... args) {
        super(MessageFormat.format(msgWithPattern, args), cause);
        this.code = code;
    }
}
