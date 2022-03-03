package com.coderbuff.third2resttemplateprop.study.handlerInterceptor;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: UserMobileMethodArgumentResolver
 * @Author yuke
 * @Date: 2022/3/3 21:51
 */

public class UserMobileMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserMobile.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        return servletRequest.getAttribute("HeaderCons.USER_MOBILE");
    }
}
