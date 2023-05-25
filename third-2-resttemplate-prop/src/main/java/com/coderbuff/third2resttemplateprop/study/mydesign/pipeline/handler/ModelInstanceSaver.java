package com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.handler;

import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.pipeline.InstanceBuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author 喻可
 * @Date 2021/10/27 17:15
 */
@Component
public class ModelInstanceSaver implements ContextHandler<InstanceBuildContext> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handle(InstanceBuildContext context) {
        logger.info("--保存模型实例到相关DB表--");

        // 假装保存模型实例

        return true;
    }
}