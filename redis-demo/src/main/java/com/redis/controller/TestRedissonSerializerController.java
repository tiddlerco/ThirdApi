package com.redis.controller;

import com.redis.StockDTO;
import com.redis.config.RedisClient;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * redisson JacksonSerializer序列化
 */

@RestController
public class TestRedissonSerializerController {

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/testRedissonSerializer")
    public String testRedissonSerializer() {

        StockDTO stockDTO = new StockDTO();
        stockDTO.setPrice("1212");
        stockDTO.setName("test");
        redissonClient.getBucket("testRedisson").set(stockDTO);

        StockDTO stockDTO2  = (StockDTO) redissonClient.getBucket("testRedisson").get();

        System.out.println(stockDTO2);

        return "success";
    }

}
