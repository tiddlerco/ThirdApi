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
public class CommonHeadHandler implements ContextHandler<PipelineContext> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handle(PipelineContext context) {
        logger.info("管道开始执行：context={}", JSON.toJSONString(context));

        // 设置开始时间
        context.setStartTime(LocalDateTime.now());

        return true;
    }
}