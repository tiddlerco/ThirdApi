package com.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    public static final int _1W = 10000;

    //布隆过滤器里预计要插入多少数据
    public static int size = 1000 * _1W;
    //误判率,它越小误判的个数也就越少
    public static double fpp = 0.00001;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }

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
