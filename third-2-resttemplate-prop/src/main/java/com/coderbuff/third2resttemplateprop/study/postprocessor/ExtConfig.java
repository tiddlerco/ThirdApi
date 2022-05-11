package com.coderbuff.third2resttemplateprop.study.postprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 喻可
 * @Date 2022/5/10 13:45
 */
@ComponentScan("com.coderbuff.third2resttemplateprop.study.postprocessor")
@Configuration
public class ExtConfig {
    @Bean
    public People people() {
        return new People("张三", "男");
    }
}
