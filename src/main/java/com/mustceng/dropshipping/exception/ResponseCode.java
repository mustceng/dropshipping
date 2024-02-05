package com.mustceng.dropshipping.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ResponseCode implements IResponseCode {

    SUCCESS(0, HttpStatus.OK),
    DATA_SAVE_SUCCESS(1, HttpStatus.OK),
    DATA_UPDATE_SUCCESS(2, HttpStatus.OK),
    DATA_DELETE_SUCCESS(3, HttpStatus.OK),
    DATA_NOT_FOUND(4, HttpStatus.CONFLICT),
    BAD_CREDENTIALS(5, HttpStatus.BAD_REQUEST),
    DATA_NOT_PROVIDED(6, HttpStatus.CONFLICT);



    private final Integer code;
    private final HttpStatus httpStatus;

    public Integer code() {
        return code;
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }


}
