package com.coderbuff.third2resttemplateprop.common;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * @title: Test
 * @Author yuke
 * @Date: 2022/8/10 19:58
 */
public class Test {

    public static void main(String[] args) {
        DateTime yesterday = DateUtil.yesterday();


        System.out.println(yesterday);

        String format = DateUtil.format(yesterday, "yyyy-MM-dd");

        System.out.println(format);

    }

}
