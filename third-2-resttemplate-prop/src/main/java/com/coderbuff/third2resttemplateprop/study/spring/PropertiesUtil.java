package com.coderbuff.third2resttemplateprop.study.spring;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @Author 喻可
 * @Date 2022/4/12 16:14
 */
@Component   //注意该类必须在ioc容器中使用，否则EmbeddedValueResolverAware不会注入进来
public class PropertiesUtil implements EmbeddedValueResolverAware {

    private static StringValueResolver valueResolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        valueResolver = resolver;
    }

    public static String getValue(String key) {
        //StringValueResolver还可以解析spel表达式，@Value注解能够解析的，StringValueResolver都可以解析
        return valueResolver.resolveStringValue("${" + key + "}");
    }

}
