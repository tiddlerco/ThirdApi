package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo2;

import java.util.*;

/**
 * 作者：yk
 * 描述：字典错误枚举
 */
public enum SystemErrorCodeEnum2 implements ErrorCodeEnums2 {
    // @formatter:off

    // ================系统错误码 200000-201000===================
    PARAMETER_EMPTY(200000, "请求参数为空"),

    SAVE_DCIT_TYPE_ERROR(201100, "新增字典模块失败"),
    DELETE_DCIT_TYPE_ERROR(201101, "删除字典模块失败"),
    UPDATE_DCIT_TYPE_ERROR(201102, "修改字典模块失败"),

    // ================基础模块错误码 201000-202000 ===================
    DECORATE_PARAM_JSON_HOME_NOT_EXISTS(201000, "首页装修页面不存在"),
    DECORATE_PARAM_JSON_NOT_EXISTS(201001, "装修页面不存在"),
    DECORATE_PARAM_JSON_ERROR(201002, "装修页面JSON解析异常"),

    FREIGHT_TEMPLATE_NOT_EXISTS(201010, "运费模板不存在"),
    FREIGHT_CHARGE_MODE_NOT_EXISTS(201011, "当前运费规则类型不存在"),
    FREIGHT_RULES_NOT_EXISTS(201012, "当前没有运费规则"),
    FREIGHT_DELIVERY_NOT_SUPPORTED(201013, "该省份不支持配送"),
    FREIGHT_NUMBER_ERROR(201014, "件数不达标"),
    FREIGHT_GOODS_FAILE(201015, "调用远程商品接口【orderGoodsInfo】失败,请检查商品模块"),

    FREIGHT_CALC_GOODS_WEIGHT_FAILE(201016, "计算商品重量失败"),
    FREIGHT_CALC_GOODS_VOLUME_FAILE(201017, "计算商品体积失败"),

    DELIVERY_HUNDRED_REAL_TIME_QUERY_ERROR(201020, "调用物流接口失败"),

    ;
    // @formatter:on

    private static final Map<Integer, SystemErrorCodeEnum2> map = new HashMap<>();

    static {
        for (SystemErrorCodeEnum2 yn : SystemErrorCodeEnum2.values()) {
            map.put(yn.code, yn);
        }
    }

    private Integer code;
    private String desc;

    SystemErrorCodeEnum2(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SystemErrorCodeEnum2 fromKey(String key) {
        if (SystemErrorCodeEnum2.map.containsKey(key)) {
            return SystemErrorCodeEnum2.map.get(key);
        }
        return null;
    }

    public Map<String, Object> map() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("desc", desc);
        return result;
    }

    public Map<String, Object> map(String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        if (message != null) {
            result.put("desc", message + desc);
        }
        return result;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public ErrorCodeEnums2 setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public List<String> getMsg() {
        List<String> list = new ArrayList<>();
        list.add(desc);
        list.add("添加一条测试数据");
        return list;
    }

    public ErrorCodeEnums2 setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
