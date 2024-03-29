package com.redis.controller;

import com.alibaba.fastjson.JSON;
import com.redis.StockDTO;
import com.redis.config.RedisClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestFastJsonSerializerController {

    @GetMapping("/testFastJsonSerializer")
    public String testFastJsonSerializer() {

        StockDTO stockDTO = new StockDTO();
        stockDTO.setPrice("1212");
        stockDTO.setName("test");

        RedisClient.set("stockDTO",stockDTO);

        //可直接序列化为对象
        Object jsonString = RedisClient.get("stockDTO");

        StockDTO stockDTO1 = JSON.parseObject(jsonString.toString(), StockDTO.class);

        System.out.println(stockDTO1);
        return "success";
    }


    @GetMapping("/testFastJsonSerializer2")
    public String testFastJsonSerializer2() {

        RedisClient.set("testKey",3);

        RedisClient.set("testKey2","testValue");

        Integer testKey = RedisClient.get("testKey");

        System.out.println(testKey);

        String testKey2 = RedisClient.get("testKey2");

        System.out.println(testKey2);

        return "success";
    }

    @GetMapping("/testFastJsonSerializer3")
    public String testFastJsonSerializer3() {

        HashMap<String, String> map = new HashMap<>();
        map.put("test1", "testValue1");
        map.put("test2", "testValue2");

        RedisClient.set("testMap",map);

        //这种取法会报错，不能直接反序列化为HashMap
        HashMap<String, String> map2 = RedisClient.get("testMap");

        System.out.println(map2);

        return "success";
    }

}
