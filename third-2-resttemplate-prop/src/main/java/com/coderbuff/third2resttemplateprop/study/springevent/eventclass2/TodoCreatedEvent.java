package com.coderbuff.third2resttemplateprop.study.springevent.eventclass2;


import com.coderbuff.third2resttemplateprop.study.springevent.entity.TodoEventData;

/**
 * @Author 喻可
 * @Date 2021/7/19 10:36
 */

public class TodoCreatedEvent extends BaseEvent<TodoEventData> {

    public TodoCreatedEvent(Object source, TodoEventData eventData) {
        super(source, eventData);
    }
}
