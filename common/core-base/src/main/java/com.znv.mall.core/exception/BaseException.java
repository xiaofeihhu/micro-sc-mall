package com.znv.mall.core.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    /**
     * 异常对应的错误类型
     */
    private ResultCode errorType;

    /**
     * 默认是系统异常
     */
    public BaseException() {
        this.errorType = ResultCode.FAIL;
    }

    public BaseException(ResultCode errorType) {
        this.errorType = errorType;
    }

    public BaseException(ResultCode errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BaseException(ResultCode errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
}
