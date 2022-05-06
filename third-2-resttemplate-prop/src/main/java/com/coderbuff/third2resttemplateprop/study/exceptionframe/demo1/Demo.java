package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo1;

/**
 * @Author 喻可
 * @Date 2022/5/6 10:05
 */
public class Demo {

    public static void main(String[] args) {

        //测试1
        QingquRunTimeException exception = QingquExceptionFactory.build(SystemErrorCodeEnum.DELIVERY_HUNDRED_REAL_TIME_QUERY_ERROR);

        System.out.println(exception.toString());

        //测试2
        QingquRunTimeException build = QingquExceptionFactory.build(SystemErrorCodeEnum.PARAMETER_EMPTY,
                //这里设置需要回调的内容
                msg -> msg + " PrivacyPolicy ConfigInfo id不能为空");

        System.out.println(build.toString());


    }


}
