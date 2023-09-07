package com.coderbuff.third2resttemplateprop.study.functinonInterface.consumer3;

import com.coderbuff.third2resttemplateprop.study.functinonInterface.consumer2.SaParamFunction;

/**
 * @Author tiddler
 * @Date 2023/9/7 08:23
 */
public class OrderProcessor {
    public void processOrder(SaParamFunction<Order> orderCommand, Order order) {
        orderCommand.run(order);
    }
}
