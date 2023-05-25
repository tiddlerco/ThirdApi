package com.coderbuff.third2resttemplateprop.study.mydesign.strategy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;

/**
 * @Author 喻可
 * @Date 2021/12/24 11:41
 */
@Component
public class CmdbItemOperationFactory implements InitializingBean {

    private static final
    EnumMap<OperateType, CmdbItemOperationAbstract> OPERATION_MAP = new EnumMap<>(OperateType.class);

    /**
     *
     * @param type
     * @return
     */
    public CmdbItemOperationAbstract getOperation(OperateType type) {
        return OPERATION_MAP.get(type);
    }

    //这里的factory可以完全实现开闭原则,这里使用@Bean就会报错
    @Autowired
    public void setOperation(List<CmdbItemOperationAbstract> operations) {
        for (final CmdbItemOperationAbstract operation : operations) {
            System.out.println("CmdbItemOperationFactory=="+operation.getOperationType());
            OPERATION_MAP.put(operation.getOperationType(), operation);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("CmdbItemOperationFactory初始化");
    }
}
