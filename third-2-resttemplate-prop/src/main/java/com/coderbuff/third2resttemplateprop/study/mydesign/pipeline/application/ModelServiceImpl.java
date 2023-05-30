package com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.application;

import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.pipeline.InstanceBuildContext;
import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.executor.PipelineExecutor;
import com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.pipeline.PipelineContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

/**
 * @Author 喻可
 * @Date 2021/10/27 17:28
 */
@Service
public class ModelServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PipelineExecutor pipelineExecutor;

    /**
     * 提交模型（构建模型实例）
     */
    public boolean buildModelInstance() throws ExecutionException, InterruptedException {
        InstanceBuildContext data = createPipelineData();
        //异步获取
        String asyncResult = pipelineExecutor.acceptAsync(data, new BiConsumer<PipelineContext, String>() {
            @Override
            public void accept(PipelineContext pipelineContext, String result) {
                System.out.println("========回调逻辑  result=====" + result);
            }
        });
        Future<String> stringFuture = pipelineExecutor.acceptSync(data);
        String result = stringFuture.get();

        System.out.println("===============" + result);

        // 创建模型实例成功
        if (asyncResult.equals("success")) {
            return true;
        }

        logger.error("创建模式实例失败：{}", data.getErrorMsg());
        return false;
    }

    private InstanceBuildContext createPipelineData() {
        InstanceBuildContext request = new InstanceBuildContext();
        request.setModelId(1L);
        request.setUserId(111L);
        request.setStartTime(LocalDateTime.now());

        HashMap<String, Object> map = new HashMap<>();
        map.put("instanceName","1");
        request.setFormInput(map);

        return request;
    }

}
