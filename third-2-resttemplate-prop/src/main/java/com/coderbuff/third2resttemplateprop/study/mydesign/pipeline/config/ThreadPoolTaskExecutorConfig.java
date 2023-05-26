package com.coderbuff.third2resttemplateprop.study.mydesign.pipeline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 此线程池配置和AsyncConfig配置重复
 * 是否可以只使用AsyncConfig同时实现异步注解和CompletableFuture?
 * CompletableFuture和ThreadPoolExecutor是juc下的
 * ThreadPoolTaskExecutor是spring下的
 * @Author 喻可
 * @Date 2021/10/28 9:43
 */
@Configuration
public class ThreadPoolTaskExecutorConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int i = Runtime.getRuntime().availableProcessors();//获取到服务器的cpu内核
        executor.setCorePoolSize(i);//核心池大小
        executor.setMaxPoolSize(100);//最大线程数
        executor.setQueueCapacity(1000);//队列程度
        executor.setKeepAliveSeconds(1000);//线程空闲时间
        executor.setThreadNamePrefix("tsak-asyn");//线程前缀名称
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//配置拒绝策略
        executor.initialize();
        return executor;
    }
}