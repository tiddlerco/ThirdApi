package com.coderbuff.third1simple.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.coderbuff.third1simple.StockDTO;
import com.coderbuff.third1simple.config.RedisClient;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @title: 秒杀服务
 * @Author yuke
 * @Date: 2022/5/14 13:42
 */
@RestController
public class SkillGoodsController {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;


    @SentinelResource(value = "getSemaphore", blockHandler = "exceptionHandler")
    @GetMapping("/getSemaphore")
    public String testRedis() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RSemaphore rSemaphore = redissonClient.getSemaphore("SkillCount");
        boolean canGet = rSemaphore.tryAcquire(1);
        if (canGet) {
            return "success";
        }
        return "抢购失败";
    }

    public String exceptionHandler(BlockException e) {
        return "add被限流了";
    }


}
