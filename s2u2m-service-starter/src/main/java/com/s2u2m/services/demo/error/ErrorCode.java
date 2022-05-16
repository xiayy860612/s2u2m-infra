package com.s2u2m.services.demo.error;

import com.s2u2m.services.core.error.ExceptionDetails;

public enum ErrorCode implements ExceptionDetails {
    INPUT_INVALID(1, "input invalid"),
    ;

    private final int code;
    private final String pattern;
    ErrorCode(int code, String pattern) {
        this.code = code;
        this.pattern = pattern;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
