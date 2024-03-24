package com.redis.controller;


import com.alibaba.fastjson.JSON;
import com.redis.StockDTO;
import com.redis.config.RedisClient;
import jakarta.annotation.Resource;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


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
    RBloomFilter<String> phoneFilter;



    @GetMapping("/testReentrantLock")
    public String testReentrantLock() throws InterruptedException {

        RedisClient.setString("testOne","value");


        Object testOne = RedisClient.get("testOne");

        System.out.println(testOne.toString());


        StockDTO stockDTO = new StockDTO();
        stockDTO.setName("测试名称");
        stockDTO.setPrice("测试价格");
        String jsonString = JSON.toJSONString(stockDTO);

        RedisClient.setString("stockDTO",jsonString);

        redissonClient.getBucket("string").set("value");

        return "success";
    }

    public void test() throws InterruptedException {
        RLock clientLock = redissonClient.getLock("reentrantLock");
        clientLock.lock();
        Thread.sleep(5000);
        clientLock.unlock();
    }


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


    @GetMapping("/testAsyncReentrantLock")
    public void testAsyncReentrantLock() throws ExecutionException, InterruptedException {
        RLock lock = redissonClient.getLock("anyLock");
        try{
            lock.lockAsync();
            lock.lockAsync(10, TimeUnit.SECONDS);
            System.out.println("准备获取锁");
            Future<Boolean> res = lock.tryLockAsync(3, 10, TimeUnit.SECONDS);
            RFuture<Void> voidRFuture = lock.lockAsync();
            if(res.get()){
                System.out.println("执行业务方法");
            }
        } finally {
            System.out.println("释放锁");
            lock.unlock();
        }

    }




}
