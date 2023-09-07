package com.coderbuff.third2resttemplateprop.study.functinonInterface.consumer3;

/**
 * @Author tiddler
 * @Date 2023/9/7 08:24
 */
public class RestaurantOrderSystem {


    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();

        Order order1 = new Order("Pizza");
        Order order2 = new Order("Burger");


        // 使用 lambda 表达式下订单
        orderProcessor.processOrder(order -> {
            System.out.println("Order placed for " + order.getDishName());
        }, order1);


        orderProcessor.processOrder(order -> {
            System.out.println("Order placed for " + order.getDishName());
        }, order2);


        // 使用 lambda 表达式取消订单
        orderProcessor.processOrder(order -> {
            if (!order.isCanceled()) {
                System.out.println("Canceling order for " + order.getDishName());
                order.cancelOrder();
            } else {
                System.out.println("Order is already canceled for " + order.getDishName());
            }
        }, order1);


        // 使用 lambda 表达式检查订单状态
        orderProcessor.processOrder(order -> {
            if (order.isCanceled()) {
                System.out.println("Order for " + order.getDishName() + " is canceled.");
            } else {
                System.out.println("Order for " + order.getDishName() + " is not canceled.");
            }
        }, order1);
    }


}
