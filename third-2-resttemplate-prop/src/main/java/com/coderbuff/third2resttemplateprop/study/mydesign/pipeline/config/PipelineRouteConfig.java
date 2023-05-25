package com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.config;

/**
 * @Author 喻可
 * @Date 2021/10/27 17:19
 */

import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.handler.*;
import com.coderbuff.third2resttemplateprop.study.pipeline.handler.*;
import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.pipeline.InstanceBuildContext;
import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.pipeline.PipelineContext;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 管道路由的配置
 * <p>
 * 当一个类实现了这个接口（ApplicationContextAware）之后，
 * 这个类就可以方便获得ApplicationContext中的所有bean。
 * 换句话说，就是这个类可以直接获取spring配置文件中，所有引用到的bean对象。
 */
@Configuration
public class PipelineRouteConfig implements ApplicationContextAware {

    /**
     * 数据类型->管道中处理器类型列表 的路由
     */
    private static final
    Map<Class<? extends PipelineContext>,
            List<Class<? extends ContextHandler<? extends PipelineContext>>>> PIPELINE_ROUTE_MAP = new HashMap<>(4);

    /*
     * 在这里配置各种上下文类型对应的处理管道：键为上下文类型，值为处理器类型的列表
     */
    static {
        PIPELINE_ROUTE_MAP.put(InstanceBuildContext.class,
                Arrays.asList(
                        InputDataPreChecker.class,
                        BizSideCustomProcessor.class,
                        FormInputPreprocessor.class,
                        ModelInstanceCreator.class,
                        ModelInstanceSaver.class
                ));

        // 将来其他 Context 的管道配置
    }

    /**
     * 在 Spring 启动时，根据路由表生成对应的管道映射关系
     */
    @Bean("pipelineRouteMap")
    public Map<Class<? extends PipelineContext>, List<? extends ContextHandler<? extends PipelineContext>>> getHandlerPipelineMap() {
        return PIPELINE_ROUTE_MAP.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, this::toPipeline));
                //等价于.collect(Collectors.toMap(item->item.getKey(), item->toPipeline(item)));
    }

    /**
     * 根据给定的管道中 ContextHandler 的类型的列表，构建管道
     */
    private List<? extends ContextHandler<? extends PipelineContext>> toPipeline(
            Map.Entry<Class<? extends PipelineContext>, List<Class<? extends ContextHandler<? extends PipelineContext>>>> entry) {
        return entry.getValue()
                .stream()
                .map(appContext::getBean)
                .collect(Collectors.toList());
    }

    private ApplicationContext appContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
