package com.coderbuff.third2resttemplateprop.study.mydesign.strategy;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author 喻可
 * @Date 2021/12/24 09:42
 */
@Component
//@Order生效的前提条件是实现CommandLineRunner接口
@Order(value = 2)
public class DeleteItemOperation extends CmdbItemOperationAbstract implements InitializingBean, CommandLineRunner {


    /**
     * 获取操作类型
     */
    @Override
    public OperateType getOperationType() {
        return OperateType.DELETE_ITEM;
    }

    @Override
    public void doAction() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("DeleteItemOperation初始化");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DeleteItemOperation CommandLineRunner");
    }
}
