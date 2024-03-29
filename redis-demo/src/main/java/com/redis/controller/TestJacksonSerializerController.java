package com.redis.controller;

import com.redis.StockDTO;
import com.redis.config.RedisClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * redis JacksonSerializer序列化
 */

@RestController
public class TestJacksonSerializerController {

    @GetMapping("/testJacksonSerializer")
    public String testJacksonSerializer() {

        StockDTO stockDTO = new StockDTO();
        stockDTO.setPrice("1212");
        stockDTO.setName("test");

        RedisClient.set("stockDTO",stockDTO);

        //可直接序列化为对象
        StockDTO stockDTOSerializer = RedisClient.get("stockDTO");

        System.out.println(stockDTOSerializer);
        return "success";
    }


    @GetMapping("/testJacksonSerializer2")
    public String testJacksonSerializer2() {

        RedisClient.set("testKey",3);

        RedisClient.set("testKey2","testValue");

        Integer testKey = RedisClient.get("testKey");

        System.out.println(testKey);

        String testKey2 = RedisClient.get("testKey2");

        System.out.println(testKey2);

        return "success";
    }

    @GetMapping("/testJacksonSerializer3")
    public String testFastJsonSerializer3() {

        HashMap<String, String> map = new HashMap<>();
        map.put("test1", "testValue1");
        map.put("test2", "testValue2");

        RedisClient.set("testMap",map);

        //直接反序列化为HashMap
        HashMap<String, String> map2 = RedisClient.get("testMap");

        System.out.println(map2);

        return "success";
    }

}
