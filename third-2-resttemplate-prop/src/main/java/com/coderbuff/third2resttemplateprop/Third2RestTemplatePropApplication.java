package com.coderbuff.third2resttemplateprop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.coderbuff.third2resttemplateprop.mapper")
public class Third2RestTemplatePropApplication {

    public static void main(String[] args) {
        System.out.println("The service to start.");
        SpringApplication.run(Third2RestTemplatePropApplication.class, args);
        System.out.println("The service has started.");
    }

}
