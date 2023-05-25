package com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.handler;

import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.pipeline.InstanceBuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author 喻可
 * @Date 2021/10/27 18:47
 */
@Component
public class FormInputPreprocessor implements ContextHandler<InstanceBuildContext> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handle(InstanceBuildContext context) {
        logger.info("--表单输入数据预处理--");

        // 假装进行表单输入数据预处理

        return true;
    }
}
