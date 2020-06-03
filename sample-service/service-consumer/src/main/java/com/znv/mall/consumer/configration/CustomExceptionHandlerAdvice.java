package com.znv.mall.consumer.configration;

import com.znv.mall.core.entity.vo.Result;
import com.znv.mall.core.exception.CustomException;
import com.znv.mall.core.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public Result handleException(CustomException e) {
        // 打印异常信息
        log.error("### 异常信息:{} ###", e.getMessage());
        return new Result(e.getResultCode());
    }
}