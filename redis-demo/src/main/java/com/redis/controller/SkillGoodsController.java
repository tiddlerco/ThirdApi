package com.redis.controller;


import com.redis.config.RedisClient;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Resource(name = "phoneFilter")
    RBloomFilter<String> phoneFilter;


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
        boolean testBloomKey = phoneFilter.add("testBloomKey");
        System.out.println(testBloomKey);
        RedisClient.set("testBloomKey","testBloomValue");
        return "success";
    }


    @GetMapping("/testBitMap")
    public String testBitMap() {
        RedisClient.setBit("testBit", 851813273773800960L, true);
        RedisClient.setBit("testBit", 851812399886368000L, true);
        RedisClient.setBit("testBit", 851809222432653056L, true);
        RedisClient.setBit("testBit", 851797367937040000L, true);
        Boolean testBit = RedisClient.getBit("testBit", 851813273773800960L);
        Long testBit1 = RedisClient.bitCount("testBit");
        System.out.println(testBit);
        System.out.println(testBit1);

        return "success";
    }

    @GetMapping("/testGetBloomFilter")
    public String testGetBloomFilter() {
        boolean testBloomKey = phoneFilter.contains("testBloomKey");
        System.out.println(testBloomKey);
        if(testBloomKey){
            return "抢购成功";
        }

        return "抢购失败";
    }





}
