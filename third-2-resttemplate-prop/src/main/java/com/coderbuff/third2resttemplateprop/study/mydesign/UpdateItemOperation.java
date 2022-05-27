package com.coderbuff.third2resttemplateprop.study.mydesign;


import com.coderbuff.third2resttemplateprop.study.spring.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author 喻可
 * @Date 2021/12/24 09:42
 */
@Slf4j
@Component
public class UpdateItemOperation extends CmdbItemOperationAbstract {


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

}
