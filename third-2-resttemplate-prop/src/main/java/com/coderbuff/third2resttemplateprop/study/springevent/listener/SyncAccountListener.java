package com.coderbuff.third2resttemplateprop.study.springevent.listener;

import com.coderbuff.third2resttemplateprop.study.springevent.eventclass2.AccountCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author 喻可
 * @Date 2021/7/19 11:03
 */
@Slf4j
@Component
public class SyncAccountListener {

    @EventListener
    public void doOnNormalEvent(AccountCreatedEvent event) {
        try {

            System.out.println("==非异步输出=="+event.getEventData().getName()+"====");

            System.out.println("非异步开始睡眠");
            Thread.sleep(3000);
            System.out.println("非异步睡眠结束");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
