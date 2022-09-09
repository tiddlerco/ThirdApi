package com.coderbuff.third2resttemplateprop.study.springevent.eventclass2;


import com.coderbuff.third2resttemplateprop.study.springevent.entity.AccountEventData;

/**
 * @Author 喻可
 * @Date 2021/7/19 10:35
 */
public class AccountCreatedEvent extends BaseEvent<AccountEventData> {

    public AccountCreatedEvent(Object source, AccountEventData eventData) {
        super(source, eventData);
    }
}


