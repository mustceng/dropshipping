package com.mustceng.dropshipping.exception;

import org.springframework.http.HttpStatus;

public interface IResponseCode {
    Integer code();

    HttpStatus httpStatus();
}
