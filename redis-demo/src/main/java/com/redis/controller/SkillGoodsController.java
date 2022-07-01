package com.redis.controller;


import com.redis.config.RedisClient;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @title: 秒杀服务
 * @Author yuke
 * @Date: 2022/5/14 13:42
 */
@RestController
@RequestMapping("/api/skill")
public class SkillGoodsController {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @Resource
    RBloomFilter<String> rBloomFilter;


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



    @GetMapping("/testAddBloomFilter")
    public String testAddBloomFilter() {
        boolean testBloomKey = rBloomFilter.add("testBloomKey");
        System.out.println(testBloomKey);
        RedisClient.set("testBloomKey","testBloomValue");
        return "success";
    }

    @GetMapping("/testGetBloomFilter")
    public String testGetBloomFilter() {
        boolean testBloomKey = rBloomFilter.contains("testBloomKey");
        System.out.println(testBloomKey);
        RedisClient.set("testBloomKey","testBloomValue");
        return "抢购失败";
    }





}
