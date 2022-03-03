package com.coderbuff.third2resttemplateprop.study.handlerInterceptor;


import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserId {

}
