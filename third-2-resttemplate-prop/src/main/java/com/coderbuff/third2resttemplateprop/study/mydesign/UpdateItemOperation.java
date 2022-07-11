package com.coderbuff.third2resttemplateprop.study.mydesign;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @Author 喻可
 * @Date 2021/12/24 09:42
 */
@Slf4j
@Component
//@Order生效的前提条件是实现CommandLineRunner接口，或者是在切面里（理解有待深入）
@Order(value = 1)
public class UpdateItemOperation extends CmdbItemOperationAbstract implements InitializingBean, CommandLineRunner {


    /**
     * 获取操作类型
     */
    @Override
    public OperateType getOperationType() {
        return OperateType.UPDATE_ITEM;
    }

    @Override
    public void doAction() {

        System.out.println("UpdateItemOperation方法对doAction进行重写");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UpdateItemOperation初始化");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("UpdateItemOperation CommandLineRunner");
    }

}
