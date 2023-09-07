package com.coderbuff.third2resttemplateprop.study.functinonInterface.consumer3;

import lombok.Data;

/**
 * @Author tiddler
 * @Date 2023/9/7 08:23
 */
@Data
public class Order {
    private String dishName;
    private boolean isCanceled;

    public Order(String dishName) {
        this.dishName = dishName;
        this.isCanceled = false;
    }
    public void cancelOrder() {
        isCanceled = true;
    }
}