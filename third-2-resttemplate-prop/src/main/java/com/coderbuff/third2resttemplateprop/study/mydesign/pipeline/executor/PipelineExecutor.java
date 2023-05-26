package com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.executor;

/**
 * @Author 喻可
 * @Date 2021/10/27 17:21
 */

import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.handler.CommonHeadHandler;
import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.handler.CommonTailHandler;
import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.handler.ContextHandler;
import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.pipeline.PipelineContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

/**
 * 管道执行器
 */
@Component
public class PipelineExecutor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 管道线程池
     */
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private CommonHeadHandler commonHeadHandler;

    @Autowired
    private CommonTailHandler commonTailHandler;

    /**
     * 引用 PipelineRouteConfig 中的 pipelineRouteMap
     * 这里为什么是super的解释在下面
     */
    @Resource
    private Map<Class<? extends PipelineContext>,
                List<? extends ContextHandler<? extends PipelineContext>>> pipelineRouteMap;

    /**
     * 同步处理输入的上下文数据<br/>
     * 如果处理时上下文数据流通到最后一个处理器且最后一个处理器返回 true，则返回 true，否则返回 false
     *
     * @param context 输入的上下文数据
     * @return 处理过程中管道是否畅通，畅通返回 true，不畅通返回 false
     */
    @Async
    public Future<String> acceptSync(PipelineContext context) {

        System.out.println("===Async注解====acceptSync======" + Thread.currentThread().getName());

        Objects.requireNonNull(context, "上下文数据不能为 null");
        // 拿到数据类型
        Class<? extends PipelineContext> dataType = context.getClass();
        List<? extends ContextHandler<? extends PipelineContext>> pipeline = pipelineRouteMap.get(dataType);

        if (CollectionUtils.isEmpty(pipeline)) {
            logger.error("{} 的管道为空", dataType.getSimpleName());
            return new AsyncResult<>("false");
        }

        // 【通用头处理器】处理
        commonHeadHandler.handle(context);

        // 管道是否畅通
        boolean lastSuccess = true;

        for (ContextHandler<? extends PipelineContext> handler : pipeline) {
            try {
                // 当前处理器处理数据，并返回是否继续向下处理
                lastSuccess = ((ContextHandler<PipelineContext>) handler).handle(context);
            } catch (Throwable ex) {
                lastSuccess = false;
                logger.error("[{}] 处理异常，handler={}", context.getName(), handler.getClass().getSimpleName(), ex);
            }

            // 不再向下处理
            if (!lastSuccess) { break; }
        }

        // 【通用尾处理器】处理
        commonTailHandler.handle(context);

        return new AsyncResult<>("success");
    }

    /**
     * 异步处理输入的上下文数据
     * 参考AsyncConfig的使用  配置ThreadPoolTaskExecutor然后使用@Async实现异步
     * @param context  上下文数据
     * @param callback 处理完成的回调
     */
    public String acceptAsync(PipelineContext context, BiConsumer<PipelineContext, String> callback) throws ExecutionException, InterruptedException {
        Future<String> submit = taskExecutor.submit(() -> {

            System.out.println("======acceptAsync=====" + Thread.currentThread().getName());

            //注意 @Async是基于aop实现的  这里相当于本类调用acceptSync方法，会导致@Async失效，ModelServiceImpl里的调用方式就不会
            Future<String> stringFuture = acceptSync(context);

            if (callback != null) {
                callback.accept(context, stringFuture.get());
            }
            return stringFuture.get();
        });
        return submit.get();
    }

}