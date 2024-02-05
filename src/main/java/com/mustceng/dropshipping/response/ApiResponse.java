package com.mustceng.dropshipping.response;

import com.mustceng.dropshipping.exception.IResponseCode;
import com.mustceng.dropshipping.exception.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {

    private MetaResult meta;

    private T body;

    public static <T> ApiResponse<T> of(T body) {
        return ApiResponse.<T>builder()
                .meta(MetaResultBuilder.of(ResponseCode.SUCCESS).build())
                .body(body)
                .build();
    }

    public static <T> ApiResponse<T> of(T body, IResponseCode responseCode, String... args) {
        return ApiResponse.<T>builder()
                .meta(MetaResultBuilder.of(responseCode, args).build())
                .body(body)
                .build();
    }

    public static ApiResponse success() {
        return ApiResponse.builder()
                .meta(MetaResultBuilder.of(ResponseCode.SUCCESS).build())
                .build();
    }

    public static ApiResponse success(IResponseCode responseCode, String... args) {
        return ApiResponse.builder()
                .meta(MetaResultBuilder.of(responseCode, args).build())
                .build();
    }
}
