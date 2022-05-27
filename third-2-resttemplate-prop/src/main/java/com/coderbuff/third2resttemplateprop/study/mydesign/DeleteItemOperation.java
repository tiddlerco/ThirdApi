package com.coderbuff.third2resttemplateprop.study.mydesign;


import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author 喻可
 * @Date 2021/12/24 09:42
 */
@Component
public class DeleteItemOperation extends CmdbItemOperationAbstract {


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
}
