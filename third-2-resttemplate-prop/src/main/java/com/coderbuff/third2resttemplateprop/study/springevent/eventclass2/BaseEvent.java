package com.coderbuff.third2resttemplateprop.study.springevent.eventclass2;

import org.springframework.context.ApplicationEvent;

/**
 * 事件类：定义事件，继承ApplicationEvent的类成为一个事件类
 * 相对于eventclass1使用泛型便于拓展
 *
 * @Author 喻可
 * @Date 2021/7/19 10:33
 */
public abstract class BaseEvent<T> extends ApplicationEvent {

    /**
     * 该类型事件携带的信息
     */
    private T eventData;

    /**
     * @param source    最初触发该事件的对象
     * @param eventData 该类型事件携带的信息
     */
    public BaseEvent(Object source, T eventData) {
        super(source);
        this.eventData = eventData;
    }

    public T getEventData() {
        return eventData;
    }
}
