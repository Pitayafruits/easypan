package com.cc.annotaion;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 参数校验拦截器
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface GlobalInterceptor {

    /**
     * 参数校验
     * @return
     */
    boolean checkParams() default false;
}
