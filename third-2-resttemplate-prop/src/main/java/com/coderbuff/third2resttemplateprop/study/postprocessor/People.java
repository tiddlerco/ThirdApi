package com.coderbuff.third2resttemplateprop.study.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author 喻可
 * @Date 2022/5/10 13:45
 */
public class People implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, CommandLineRunner {
    private String name;
    private String sex;

    public People() {
        System.out.println("People无参构造器");

    }

    public People(String name, String sex) {
        System.out.println("People有参构造器:[name=" + name + ",sex=" + sex + "]");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("[People]调用了BeanFactoryAware的setBeanFactory方法了：" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("[People]调用了BeanNameAware的setBeanName方法了:" + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[People]调用了DisposableBean的destroy方法了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[People]调用了Initailization的afterPropertiesSet方法了");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("[People]调用了ApplicationContextAware的setApplicationContext方法了");
    }

    @Override
    public String toString() {
        return "People [name=" + name + ", sex=" + sex
                + "]";
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("[People]调用了CommandLineRunner的run方法了");
    }
}
