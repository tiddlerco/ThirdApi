package com.coderbuff.third2resttemplateprop.study.functinonInterface.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author tiddler
 * @Date 2023/8/21 20:02
 */
public class DataProcessor<T> {

    public void processData(List<T> data, Callback<T> callback) {
        List<T> processedData = new ArrayList<>();

        for (T item : data) {
            // 假设在处理过程中将每个元素转换为大写
            T processedItem = (T) item.toString().toUpperCase();
            processedData.add(processedItem);
        }

        // 调用回调函数，将处理后的数据传递给回调
        callback.callback(processedData);
    }


    public static void main(String[] args) {
        DataProcessor<String> dataProcessor = new DataProcessor<>();

        // 定义回调函数
        Callback<String> callback = processedList -> {
            System.out.println("Processed data: " + processedList);
        };

        List<String> inputData = Arrays.asList("apple", "banana", "cherry", "date");


        // 定义回调函数
        Callback<String> callback2 = processedList -> {
            String s = processedList.get(0);
            System.out.println("====" + s);
        };


        // 处理数据并传递回调
        dataProcessor.processData(inputData, callback);

        // 处理数据并传递回调
        dataProcessor.processData(inputData, callback2);



        System.out.println("Data processing started...");
    }

}
