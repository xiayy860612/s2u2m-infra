package com.s2u2m.services.demo.error;

import com.s2u2m.services.core.error.ErrorCode;

public enum DemoErrorCode implements ErrorCode {
    INPUT_INVALID(1, "input invalid"),
    ;

    private final int code;
    private final String message;
    DemoErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getValue() {
        return this.code;
    }

    @Override
    public String getCommonMessage() {
        return this.message;
    }
}
