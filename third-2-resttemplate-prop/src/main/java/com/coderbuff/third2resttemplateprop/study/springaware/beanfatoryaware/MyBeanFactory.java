package com.coderbuff.third2resttemplateprop.study.springaware.beanfatoryaware;

import com.coderbuff.third2resttemplateprop.study.springaware.beannameaware.MyBeanName;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @Author 喻可
 * @Date 2022/3/23 10:30
 */
@Component
public class MyBeanFactory implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void getMyBeanName() {
        MyBeanName myBeanName = (MyBeanName) beanFactory.getBean("myCustomBeanName-ware");
        System.out.println("MyBeanFactory类===BeanFactoryAware" + beanFactory.isSingleton("myCustomBeanName-ware"));
    }
}
