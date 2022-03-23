package com.coderbuff.third2resttemplateprop.study.springaware.beannameaware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 喻可
 * @Date 2022/3/23 10:25
 */
@Configuration
public class Config {
    //如果name为空 注入的bean的名称为方法名称getMyBeanName
    @Bean(name = "myCustomBeanName-ware")
    public MyBeanName getMyBeanName() {
        return new MyBeanName();
    }

}
