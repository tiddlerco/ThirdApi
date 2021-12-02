package com.coderbuff.third2resttemplateprop.study.mockTest;

import org.springframework.stereotype.Service;

/**
 * @Author 喻可
 * @Date 2021/12/2 10:55
 */
@Service
public class NameService {

    public String bulidName(String name) {
        name = name + "test";
        return name;
    }

    public String getName(String name) {
        return this.bulidName(name);
    }
}
