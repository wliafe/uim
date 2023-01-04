package com.wliafe.common.exception;

import com.wliafe.common.domain.AjaxResult;
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
    public AjaxResult RuntimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常:{}", e.getMessage());
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public AjaxResult exceptionHandler(NullPointerException e) {
        log.error("空指针异常:{}", e.getMessage());
        return AjaxResult.error("空指针异常:" + e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AjaxResult exceptionHandler(Exception e) {
        log.error("未知异常:{}", e.getMessage());
        return AjaxResult.error("未知异常:" + e.getMessage());
    }
}
