package com.s2u2m.services.core.error.exception;

import com.s2u2m.services.core.error.ExceptionDetails;
import lombok.Getter;

import java.text.MessageFormat;

public abstract class BaseException extends RuntimeException {

    @Getter
    private final transient ExceptionDetails info;

    protected BaseException(ExceptionDetails info, Object... args) {
        super(MessageFormat.format(info.getPattern(), args));
        this.info = info;
    }

    protected BaseException(ExceptionDetails info, Throwable cause, Object... args) {
        super(MessageFormat.format(info.getPattern(), args), cause);
        this.info = info;
    }
}
