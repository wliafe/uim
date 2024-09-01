package com.wliafe.common.exception;

import com.wliafe.common.domain.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author wliafe
 * @date 2023/1/3 14:11
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public MyResult RuntimeExceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        System.out.println(e.getStackTrace()[0]);
        return MyResult.error(e.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public MyResult exceptionHandler(NullPointerException e) {
        log.error(e.getMessage());
        System.out.println(e.getStackTrace()[0]);
        return MyResult.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public MyResult exceptionHandler(Exception e) {
        log.error(e.getMessage());
        System.out.println(e.getStackTrace()[0]);
        return MyResult.error(e.getMessage());
    }
}
