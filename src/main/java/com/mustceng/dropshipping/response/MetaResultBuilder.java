package com.mustceng.dropshipping.response;

import com.mustceng.dropshipping.exception.IResponseCode;
import com.mustceng.dropshipping.exception.ResponseCode;
import com.mustceng.dropshipping.exception.Severity;

public class MetaResultBuilder {

    private MetaResult metaResult = new MetaResult();

    public MetaResult build() {
        return this.metaResult;
    }

    public static MetaResultBuilder builder() {
        return new MetaResultBuilder();
    }

    public static MetaResultBuilder of(IResponseCode responseCode, Object... args) {
        if (responseCode == ResponseCode.SUCCESS) {
            return new MetaResultBuilder()
                    .code(responseCode.code());
        } else {
            return new MetaResultBuilder()
                    .code(responseCode.code())
                    .addInfo(responseCode, args);
        }
    }

    public static MetaResultBuilder of(IResponseCode responseCode, Severity severity, Object... args) {
        String message = TranslateUtils.getMessage(responseCode.code().toString(), args);
        if (severity == Severity.INFO) {
            return new MetaResultBuilder()
                    .code(responseCode.code())
                    .addInfo(message);
        } else if (severity == Severity.WARN) {
            return new MetaResultBuilder()
                    .code(responseCode.code())
                    .addWarn(message);
        } else {
            return new MetaResultBuilder()
                    .code(responseCode.code())
                    .addError(message);
        }
    }

    public MetaResultBuilder code(Integer code) {
        this.metaResult.setCode(code);
        return this;
    }

    public MetaResultBuilder addInfo(String messageCode, Object... args) {
        String message = TranslateUtils.getMessage(messageCode, args);
        this.metaResult.getInfoListSafe().add(MessageItem.builder()
                .message(message)
                .build());
        return this;
    }

    private MetaResultBuilder addInfo(String message) {
        this.metaResult.getInfoListSafe().add(MessageItem.builder()
                .message(message)
                .build());
        return this;
    }

    public MetaResultBuilder addInfo(IResponseCode responseCode, Object... args) {
        String message = TranslateUtils.getMessage(responseCode.code().toString(), args);
        this.metaResult.getInfoListSafe().add(MessageItem.builder()
                .message(message)
                .build());
        return this;
    }

    public MetaResultBuilder addWarn(String messageCode, Object... args) {
        String message = TranslateUtils.getMessage(messageCode, args);
        this.metaResult.getWarnListSafe().add(MessageItem.builder()
                .message(message)
                .build());
        return this;
    }

    private MetaResultBuilder addWarn(String message) {
        this.metaResult.getWarnListSafe().add(MessageItem.builder()
                .message(message)
                .build());
        return this;
    }

    public MetaResultBuilder addWarn(IResponseCode responseCode, Object... args) {
        String message = TranslateUtils.getMessage(responseCode.code().toString(), args);
        this.metaResult.getWarnListSafe().add(MessageItem.builder()
                .message(message)
                .build());
        return this;
    }

    public MetaResultBuilder addError(String messageCode, Object... args) {
        String message = TranslateUtils.getMessage(messageCode, args);
        this.metaResult.getErrorListSafe().add(MessageItem.builder()
                .message(message)
                .build());
        return this;
    }

    private MetaResultBuilder addError(String message) {
        this.metaResult.getErrorListSafe().add(MessageItem.builder()
                .message(message)
                .build());
        return this;
    }

    public MetaResultBuilder addError(IResponseCode responseCode, Object... args) {
        String message = TranslateUtils.getMessage(responseCode.code().toString(), args);
        this.metaResult.getErrorListSafe().add(MessageItem.builder()
                .message(message)
                .build());
        return this;
    }
}
