package com.coderbuff.third2resttemplateprop.study.functinonInterface.function;

import java.util.List;

/**
 * @Author tiddler
 * @Date 2023/8/21 20:49
 */
@FunctionalInterface
public interface Callback<T, R> {
    R callback(List<T> list);
}

