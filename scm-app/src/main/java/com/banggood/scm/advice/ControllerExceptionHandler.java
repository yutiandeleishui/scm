package com.banggood.scm.advice;

import com.banggood.scm.aop.LogginAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 * 全局异常监控
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(LogginAspect.class);

    @ExceptionHandler
    public void exceptionHandler(Exception e){
        if (e instanceof BindException) {
            List<FieldError> errors = ((BindException) e).getFieldErrors();
            StringBuilder sb = new StringBuilder();
            for (FieldError fieldError : errors) {
                String defaultMessage = fieldError.getDefaultMessage();
                if(sb == null || sb.length() == 0){
                    sb.append(defaultMessage);
                }
            }
            log.info(sb.toString());
        } if(e instanceof RuntimeException){
            log.error(e.getMessage(), e);
        }else {
            // 错误原因
            log.error(e.getMessage(), e);
        }

    }
}
