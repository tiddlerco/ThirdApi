package com.redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * redis 配置
 *
 * @author yuke
 */
@Configuration
@Order(0)
public class RedisConfig {


    @Resource
    private RedisConnectionFactory factory;


    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //设置key前缀
        redisTemplate.setKeySerializer(redisKeySerializer());
        redisTemplate.setHashKeySerializer(redisKeySerializer());
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(String.class));

        /**
         * ValueSerializer序列化两种序列化方式
         * 方式1 使用ObjectMapper和Jackson2JsonRedisSerializer，保持在redis中的对象会带有class信息，可直接取出反序列化成对象
         * 方式2  使用fastJson的FastJsonRedisSerializer，保持在redis中的对象是一个json字符串
         */
        //方式1
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(objectMapper,Object.class);

        //方式2

//         FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);

        redisTemplate.setValueSerializer(serializer);

        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    //给所有的key添加一个前缀
    private RedisSerializer<String> redisKeySerializer() {
        return new StringRedisSerializer() {
            @Override
            public byte[] serialize(String string) {
                // 添加你的key前缀
                return ("yuke:" + string).getBytes();
            }
        };
    }

    @Bean
    public HashOperations<String, String, String> hashOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String, Object> valueOperatons(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, String> listOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, String> setOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, String> zSetOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    @Bean
    public RedisClient redisClient(ValueOperations<String, Object> valueOperations, HashOperations<String, String, String> hashOperations, SetOperations<String, String> setOperations, RedisTemplate redisTemplate, ListOperations<String, String> listOperations, ZSetOperations<String, String> zSetOperations) {
        return new RedisClient(valueOperations, hashOperations, setOperations, redisTemplate, listOperations,zSetOperations);
    }


//================================布隆过滤器配置========================================
    public static final int _1W = 10000;

    //布隆过滤器里预计要插入多少数据
    public static int size = 1000 * _1W;
    //误判率,它越小误判的个数也就越少
    public static double fpp = 0.00001;

    @Bean("phoneFilter")
    <T> RBloomFilter<T> rBloomFilter1(RedissonClient redissonClient) {

        RBloomFilter<T> rBloomFilter1 = redissonClient.getBloomFilter("phoneFilter");
        //初始化布隆过滤器
        rBloomFilter1.tryInit(size, fpp);

        return rBloomFilter1;
    }

    @Bean("userFilter")
    <T> RBloomFilter<T> rBloomFilter2(RedissonClient redissonClient) {

        RBloomFilter<T> rBloomFilter2 = redissonClient.getBloomFilter("userFilter");
        //初始化布隆过滤器
        rBloomFilter2.tryInit(size, fpp);

        return rBloomFilter2;
    }



}


