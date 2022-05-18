package com.coderbuff.third1simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Third1SimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Third1SimpleApplication.class, args);
    }

}
