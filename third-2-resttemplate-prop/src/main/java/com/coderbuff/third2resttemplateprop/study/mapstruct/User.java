package com.coderbuff.third2resttemplateprop.study.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author 喻可
 * @Date 2022/4/24 10:14
 */
@Data
@Accessors(chain = true)//Lombok开启链式set
public class User {
    private Long id;
    private String username;
    private String password; // 密码
    private Integer sex;  // 性别
    private LocalDate birthday; // 生日
    private LocalDateTime createTime; // 创建时间
    private String config; // 其他扩展信息，以JSON格式存储
    private String test; // 测试字段
}
