package com.coderbuff.third2resttemplateprop.study.springaware.beannameaware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @Author 喻可
 * @Date 2022/3/23 10:25
 */

//Config中已经通过@Bean的方式注入MyBeanName,bean名称为myCustomBeanName-ware
//如果这里再加上@Component，会再从容器中注入一个bean名称为myBeanName
//@Component
public class MyBeanName implements BeanNameAware {


    private String beanName;

    @Override
    public void setBeanName(String beanName) {
        System.out.println("=======");
        System.out.println(beanName);
        this.beanName=beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
