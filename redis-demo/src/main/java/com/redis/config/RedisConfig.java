package com.redis.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

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
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        //redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(String.class));
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
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


