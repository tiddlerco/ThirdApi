package com.redis.distribute;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @title: 分布式锁客户端  工厂类
 * @Author yuke
 * @Date: 2022/11/7 21:19
 */
@Component
public class DistributedLockClient {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private String uuid;

    //@Component会默认调用对象的无参构造器
    public DistributedLockClient() {
        this.uuid = UUID.randomUUID().toString();
        // 这个UUID在spring容器初始化时就已经创建好了，保证了一个服务器的uuid都是一样的
        // 同一个服务中用ThreadId区分
        // 不同服务中首先uuid就不同了，就区分开了
    }

    public DistributedRedisLock getRedisLock(String lockName) {
        return new DistributedRedisLock(stringRedisTemplate, lockName, uuid);
    }
}
