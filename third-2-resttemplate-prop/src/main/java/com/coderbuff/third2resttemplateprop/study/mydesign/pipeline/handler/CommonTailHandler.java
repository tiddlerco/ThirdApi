package com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.handler;

import com.alibaba.fastjson.JSON;
import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.pipeline.PipelineContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author 喻可
 * @Date 2021/10/27 18:09
 */
@Component
public class CommonTailHandler implements ContextHandler<PipelineContext> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handle(PipelineContext context) {
        // 设置处理结束时间
        context.setEndTime(LocalDateTime.now());

        logger.info("管道执行完毕：context={}", JSON.toJSONString(context));

        return true;
    }
}
