package com.coderbuff.third2resttemplateprop.study.springevent.listener;

import com.coderbuff.third2resttemplateprop.study.springevent.eventclass2.AccountCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author 喻可
 * @Date 2021/7/19 12:00
 */
@Component
public class AccountListener {

    @Async
    @EventListener
    public void sendEmailOnAccountCreatedEvent(AccountCreatedEvent event) {

        System.out.println("=====异步输出======"+event.getEventData().getName()+"================");

        System.out.println("异步开始睡眠");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步睡眠结束");
    }
}
