package com.coderbuff.third2resttemplateprop.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderbuff.third2resttemplateprop.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 自定义分页
 *
 * @author miemie
 * @since 2018-08-13
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserPage extends Page<User> {
    private static final long serialVersionUID = 7246194974980132237L;

    private Integer selectInt;
    private String selectStr;
    private boolean hasNext;

    public UserPage(long current, long size) {
        super(current, size);
    }
}
