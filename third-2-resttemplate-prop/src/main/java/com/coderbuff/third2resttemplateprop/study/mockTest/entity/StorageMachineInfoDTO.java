package com.coderbuff.third2resttemplateprop.study.mockTest.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 集中仓出入库管理
 * 整机清单信息
 * @Author 喻可
 * @Date 2021/6/17 20:55
 */
@Data
@Builder
public class StorageMachineInfoDTO {


    /**
     * 二级分类
     */
    private String secondClassify;

    /**
     * 三级分类
     */
    private String thirdClassify;

    /**
     * 厂商
     */
    private String factory;

    /**
     * 型号
     */
    private String type;

    /**
     * 标准机型
     */
    private String standardModel;

    /**
     * 乘风标准机型
     */
    private String model;


    /**
     * 数量
     */
    private int count;


}
