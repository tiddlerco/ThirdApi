package com.coderbuff.third2resttemplateprop.service.impl;

import org.springframework.stereotype.Service;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
