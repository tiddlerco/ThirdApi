package com.coderbuff.third2resttemplateprop.study.postprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author 喻可
 * @Date 2022/5/10 14:02
 */
public class Demo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig.class);
        People bean = context.getBean(People.class);
        System.out.println("最终" + bean.toString());
        context.close();
    }
    /**
     * [MyBeanFactoryPostProcessor]调用了postProcessBeanFactory
     * [MyBeanFactoryPostProcessor]当前beanFactory共有9个bean
     * [MyBeanFactoryPostProcessor]当前beanFactory有下面组件[org.springframework.context.annotation.internalConfigurationAnnotationProcessor, org.springframework.context.annotation.internalAutowiredAnnotationProcessor, org.springframework.context.annotation.internalCommonAnnotationProcessor, org.springframework.context.event.internalEventListenerProcessor, org.springframework.context.event.internalEventListenerFactory, extConfig, myBeanFactoryPostProcessor, myBeanPostProcessor, people]
     * PropertyValues: length=0
     * [MyBeanFactoryPostProcessor]postProcessBeanFactory方法中修改了name属性初始值了
     * PropertyValues: length=1; bean property 'name'
     * [MyBeanPostProcessor]后置处理器处理bean=【extConfig】开始
     * [MyBeanPostProcessor]后置处理器处理bean=【extConfig】完毕!
     * People有参构造器:[name=张三,sex=男]
     * [People]调用了BeanNameAware的setBeanName方法了:people
     * [People]调用了BeanFactoryAware的setBeanFactory方法了：org.springframework.beans.factory.support.DefaultListableBeanFactory@179ece50: defining beans [org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.event.internalEventListenerProcessor,org.springframework.context.event.internalEventListenerFactory,extConfig,myBeanFactoryPostProcessor,myBeanPostProcessor,people]; root of factory hierarchy
     * [People]调用了ApplicationContextAware的setApplicationContext方法了
     * [MyBeanPostProcessor]后置处理器处理bean=【people】开始
     * [People]调用了Initailization的afterPropertiesSet方法了
     * [MyBeanPostProcessor]后置处理器处理bean=【people】完毕!
     * People [name=赵四, sex=null]
     * [People]调用了DisposableBean的destroy方法了
     */
}
