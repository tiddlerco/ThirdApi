package com.coderbuff.third2resttemplateprop.study.springevent.eventclass1;


import com.coderbuff.third2resttemplateprop.study.springevent.entity.AccountEventData;

/**
 * @Author 喻可
 * @Date 2021/7/19 10:30
 */
public class AccountCreatedEvent extends AccountEvent {
    public AccountCreatedEvent(Object source, AccountEventData eventData) {
        super(source, eventData);
    }
}
