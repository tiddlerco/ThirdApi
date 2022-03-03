package com.coderbuff.third2resttemplateprop.study.handlerInterceptor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: TestAuthController
 * @Author yuke
 * @Date: 2022/3/3 21:54
 */

@Validated
@RestController
public class TestAuthController {
    @UserAuthenticate
    @GetMapping(value = "Urls.Test.TEST")
    public String testAuth(@UserId Long userId,@UserMobile String userMobile) {
        System.out.println("userId : "+ userId + "  userMobile :" + userMobile);
        return "success";
    }
}
