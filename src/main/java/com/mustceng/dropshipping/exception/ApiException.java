package com.mustceng.dropshipping.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private IResponseCode responseCode;
    private Severity severity;
    private Object[] args;

    public ApiException(IResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public ApiException(IResponseCode responseCode, Object... args) {
        this.responseCode = responseCode;
        this.args = args;
    }

    public ApiException(IResponseCode responseCode, Severity severity, Object... args) {
        this.responseCode = responseCode;
        this.severity = severity;
        this.args = args;
    }

}
