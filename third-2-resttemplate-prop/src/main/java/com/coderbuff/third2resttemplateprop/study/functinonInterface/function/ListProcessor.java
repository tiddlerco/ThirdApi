package com.coderbuff.third2resttemplateprop.study.functinonInterface.function;

import java.util.Arrays;
import java.util.List;

/**
 * @Author tiddler
 * @Date 2023/8/21 20:50
 */
public class ListProcessor<T, R> {

    public R processList(List<T> data, Callback<T, R> callback) {
        // 执行一些操作...
        // 假设这里的操作是对列表进行某种统计，并返回统计信息
        R result = callback.callback(data);
        return result;
    }

    public static void main(String[] args) {
        ListProcessor<Integer, String> listProcessor = new ListProcessor<>();

        // 定义回调函数来处理集合的平均数
        Callback<Integer, String> listStatsCallback = list -> {
            int sum = list.stream().mapToInt(Integer::intValue).sum();
            int average = sum / list.size();
            return "Sum: " + sum + ", Average: " + average;
        };

        List<Integer> dataList = Arrays.asList(10, 20, 30, 40, 50);

        // 处理列表并获取结果
        String result = listProcessor.processList(dataList, listStatsCallback);
        System.out.println("List statistics: " + result);
    }

}
