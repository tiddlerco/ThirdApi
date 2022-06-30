package com.coderbuff.third2resttemplateprop.study.handlerInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @title: WebAppConfigurer
 * @Author yuke
 * @Date: 2022/3/3 21:48
 */

//@Configuration
//public class WebAppConfigurer implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 可添加多个，这里选择拦截所有请求地址，进入后判断是否有加注解即可
//        registry.addInterceptor(new TestFilter()).addPathPatterns("/**");
//    }
//}
