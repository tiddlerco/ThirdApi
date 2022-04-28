package com.coderbuff.third2resttemplateprop.study.mapstruct;

import org.mapstruct.*;

import java.util.List;
import java.util.stream.Stream;

/**
 * https://nullpointer.pw/mapstruct%E6%9C%80%E4%BD%B3%E5%AE%9E%E8%B7%B5.html
 *
 * @Author 喻可
 * @Date 2022/4/24 10:11
 */
@MapperConfig
public interface BaseMapping<SOURCE, TARGET> {

    /**
     * 映射同名属性
     */
    TARGET sourceToTarget(SOURCE var1);

    /**
     * 反向，映射同名属性
     */
    SOURCE targetToSource(TARGET var1);

    /**
     * 映射同名属性，集合形式
     */
    List<TARGET> sourceListToTargetList(List<SOURCE> var1);

    /**
     * 反向，映射同名属性，集合形式
     */
    List<SOURCE> targetListToSourceList(List<TARGET> var1);

    /**
     * 映射同名属性，集合流形式
     */
    List<TARGET> sourceToTarget(Stream<SOURCE> stream);

    /**
     * 反向，映射同名属性，集合流形式
     */
    List<SOURCE> targetToSource(Stream<TARGET> stream);
}
