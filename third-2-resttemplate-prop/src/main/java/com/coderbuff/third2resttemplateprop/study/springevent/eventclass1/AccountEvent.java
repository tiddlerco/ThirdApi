package com.coderbuff.third2resttemplateprop.study.springevent.eventclass1;

/**
 * 事件类：定义事件，继承ApplicationEvent的类成为一个事件类
 *
 * @Author 喻可
 * @Date 2021/7/19 10:29
 */

import com.coderbuff.third2resttemplateprop.study.springevent.entity.AccountEventData;
import org.springframework.context.ApplicationEvent;

/**
 * 账户相关的事件
 */
public abstract class AccountEvent extends ApplicationEvent {

    /**
     * 该类型事件携带的信息
     */
    private AccountEventData eventData;

    /**
     *
     * @param source 最初触发该事件的对象
     * @param eventData 该类型事件携带的信息
     */
    public AccountEvent(Object source, AccountEventData eventData) {
        super(source);
        this.eventData = eventData;
    }

    public AccountEventData getEventData() {
        return eventData;
    }
}
