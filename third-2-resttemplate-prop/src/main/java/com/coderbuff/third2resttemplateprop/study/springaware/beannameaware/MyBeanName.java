package com.coderbuff.third2resttemplateprop.study.springaware.beannameaware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @Author 喻可
 * @Date 2022/3/23 10:25
 */
@Component
public class MyBeanName implements BeanNameAware {

    @Override
    public void setBeanName(String beanName) {
        System.out.println("=======");
        System.out.println(beanName);
    }
}
