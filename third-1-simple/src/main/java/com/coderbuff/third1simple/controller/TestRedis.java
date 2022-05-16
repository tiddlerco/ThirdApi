package com.coderbuff.third1simple.controller;


import com.alibaba.fastjson.JSON;
import com.coderbuff.third1simple.StockDTO;
import com.coderbuff.third1simple.config.RedisClient;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @title: TestRedis
 * @Author yuke
 * @Date: 2022/5/14 13:42
 */
@RestController
public class TestRedis {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/add")
    public void testRedis() {
        redisTemplate.opsForValue().set("name", "yuke");

        RSemaphore rSemaphore = redissonClient.getSemaphore("YYYYYY");

        rSemaphore.trySetPermits(10);

    }

    @GetMapping("/addUser")
    public void testAddUser() {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setName("股票名称");
        stockDTO.setPrice("50/股");

        RedisClient.setString("gift:channel_list:{scene}", JSON.toJSONString(stockDTO));
    }

    @GetMapping("/getCache")
    public String testGetCache() {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setName("股票名称");
        stockDTO.setPrice("50/股");
        Object yyyyyy = RedisClient.get("YYYYYY");

        return (String) yyyyyy;

    }

}
