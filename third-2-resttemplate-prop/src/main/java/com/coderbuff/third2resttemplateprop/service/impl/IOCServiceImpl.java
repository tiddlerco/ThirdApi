package com.coderbuff.third2resttemplateprop.service.impl;

import com.coderbuff.third2resttemplateprop.service.IOCService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试通过xml文件注入bean的过程
 *
 * @Author 喻可
 * @Date 2022/5/5 11:21
 */
public class IOCServiceImpl implements IOCService {
    public String hollo() {
        return "Hello,IOC";
    }

    public static void main(String args[]) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-ioc.xml");
        IOCService iocService = context.getBean(IOCService.class);
        System.out.println(iocService.hollo());
    }
}
