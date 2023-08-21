package com.coderbuff.third2resttemplateprop.study.functinonInterface.consumer;

import java.util.List;

/**
 * @Author tiddler
 * @Date 2023/8/21 20:01
 */
@FunctionalInterface
public interface Callback<T> {
    /**
     * 回调方法
     *
     * @param list 回调方法的参数
     */
    void callback(List<T> list);
}