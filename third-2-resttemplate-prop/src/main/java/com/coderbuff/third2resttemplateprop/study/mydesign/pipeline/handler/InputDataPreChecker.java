package com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.handler;

import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.pipeline.InstanceBuildContext;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author 喻可
 * @Date 2021/10/27 17:14
 */
@Component
public class InputDataPreChecker implements ContextHandler<InstanceBuildContext> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handle(InstanceBuildContext context) {
        logger.info("--输入数据校验--");

        Map<String, Object> formInput = context.getFormInput();

        if (MapUtils.isEmpty(formInput)) {
            context.setErrorMsg("表单输入数据不能为空");
            return false;
        }

        String instanceName = (String) formInput.get("instanceName");

        if (StringUtils.isBlank(instanceName)) {
            context.setErrorMsg("表单输入数据必须包含实例名称");
            return false;
        }

        return true;
    }
}
