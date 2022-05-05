package com.coderbuff.third2resttemplateprop.study.spring;

import com.coderbuff.third2resttemplateprop.study.springaware.beanfatoryaware.MyBeanFactory;
import com.coderbuff.third2resttemplateprop.study.springaware.beannameaware.MyBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author 喻可
 * @Date 2021/11/23 16:19
 */

//如果这里不加入@Component注解  run方法不会执行,三个@Resource也不会报错
//ApplicationContext也可以直接注入使用！！！
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class MyStartupRunner implements CommandLineRunner {

    @Resource
    private MyBeanName myBeanName;
    @Resource
    private MyBeanFactory myBeanFactory;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyStartupRunner类===CommandLineRunner" + myBeanName.getBeanName());
        myBeanFactory.getMyBeanName();
        MyBeanName myBeanName = (MyBeanName) applicationContext.getBean("myCustomBeanName-ware");
        System.out.println("MyStartupRunner类====CommandLineRunner");
        System.out.println(PropertiesUtil.getValue("person.sex"));
    }

}
