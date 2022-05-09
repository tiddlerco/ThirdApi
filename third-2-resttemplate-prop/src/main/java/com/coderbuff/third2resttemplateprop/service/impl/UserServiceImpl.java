package com.coderbuff.third2resttemplateprop.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderbuff.third2resttemplateprop.entity.User;
import com.coderbuff.third2resttemplateprop.mapper.UserMapper;
import com.coderbuff.third2resttemplateprop.service.UserService;

/**
 * @Author 喻可
 * @Date 2022/4/27 11:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, InitializingBean {


    //构造方法> @PostConstruct>afterPropertiesSet()

    /**
     * 最先执行
     */
    public UserServiceImpl() {
        System.out.println("=======UserServiceImpl构造方法被执行=======");
    }

    /**
     * 第二执行
     */
    @PostConstruct
    private void init() {
        System.out.println("========PostConstruct注解方法被调用==========");
    }

    /**
     * 最后执行
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("=======afterPropertiesSet方法被调用=======");
    }

}
