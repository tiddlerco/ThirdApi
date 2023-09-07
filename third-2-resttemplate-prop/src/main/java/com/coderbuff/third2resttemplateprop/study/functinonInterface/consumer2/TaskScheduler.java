package com.coderbuff.third2resttemplateprop.study.functinonInterface.consumer2;

/**
 * @Author tiddler
 * @Date 2023/9/7 08:14
 */
public class TaskScheduler {


    // 执行任务
    public <T> void scheduleTask(String taskName, SaParamFunction<T> task, T param) {
        System.out.println("Scheduling task: " + taskName);
        //通过这种方式，可以动态地执行不同类型的任务，而不需要为每个任务编写单独的执行代码。
        //任务的具体行为（例如打印消息、计算平方根等）由 run 方法的具体实现决定。
        task.run(param);
        System.out.println("Task completed: " + taskName);
        System.out.println();
    }


    public static void main(String[] args) {
        // 创建一个任务调度器
        TaskScheduler scheduler = new TaskScheduler();

        // 定义不同的任务
        SaParamFunction<Integer> calculateSquareTask = number -> {
            int square = number * number;
            System.out.println(number + " squared is: " + square);
        };

        SaParamFunction<Double> calculateSquareRootTask = number -> {
            double squareRoot = Math.sqrt(number);
            System.out.println("Square root of " + number + " is: " + squareRoot);
        };

        // 添加任务到调度器
        scheduler.scheduleTask("Print Message", message -> {
            System.out.println("Message: " + message);
        }, "Hello, Lambda!");
        scheduler.scheduleTask("Calculate Square", calculateSquareTask, 5);
        scheduler.scheduleTask("Calculate Square Root", calculateSquareRootTask, 16.0);
    }

}