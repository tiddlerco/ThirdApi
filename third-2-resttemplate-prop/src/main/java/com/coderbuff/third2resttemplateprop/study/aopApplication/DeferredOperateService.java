package com.coderbuff.third2resttemplateprop.study.aopApplication;

import org.springframework.stereotype.Service;

/**
 * @Author yuke
 * @Date 2023/11/7 17:22
 * @Description: 需要延迟操作的方法
 */
@Service
public class DeferredOperateService {

    @Deferred
    public void updateRedis() {
        System.out.println("操作缓存");
    }

    @Deferred
    public void sendMQMessage() {
        System.out.println("操作mq");
    }
}

