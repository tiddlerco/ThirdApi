package com.coderbuff.third2resttemplateprop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @title: ThreadPoolConfig
 * @Author yuke
 * @Date: 2020-09-20 10:30
 */


@Configuration
public class ThreadPoolConfig {
    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int i = Runtime.getRuntime().availableProcessors();
        //核心池大小
        executor.setCorePoolSize(i);
        //最大线程数
        executor.setMaxPoolSize(30);
        //队列程度
        executor.setQueueCapacity(50);
        //线程空闲时间
        executor.setKeepAliveSeconds(10);
        //线程前缀名称
        executor.setThreadNamePrefix("current-task");
        //配置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


}
