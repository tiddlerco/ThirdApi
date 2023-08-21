package com.coderbuff.third2resttemplateprop.study.springevent.publisher;

/**
 * 方式1：直接使用ApplicationEventPublisher(推荐)
 *
 * @Author 喻可
 * @Date 2021/7/19 10:45
 */

import com.coderbuff.third2resttemplateprop.study.spring.SpringContextUtil;
import com.coderbuff.third2resttemplateprop.study.springevent.entity.Account;
import com.coderbuff.third2resttemplateprop.study.springevent.entity.AccountEventData;
import com.coderbuff.third2resttemplateprop.study.springevent.eventclass2.AccountCreatedEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountsController {

    @Resource
    private ApplicationEventPublisher publisher;

    @GetMapping("testEvent")
    public Account createAccount() {

        AccountEventData accountEventData = new AccountEventData();

        Account account = new Account();

        account.setName("喻可");

        BeanUtils.copyProperties(account, accountEventData);

        publisher.publishEvent(new AccountCreatedEvent(this, accountEventData));

        System.out.println("发送成功");

        return account;
    }

    @GetMapping("testEvent2")
    public Account createAccount2() {

        Account account1 = new Account();
        account1.setName("testPublishEvent");
        SpringContextUtil.getApplicationContext().publishEvent(account1);

        try {
            Thread.sleep(3000);
            System.out.println("主线程执行完毕");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return account1;
    }

}
