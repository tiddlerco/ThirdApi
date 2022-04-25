package com.coderbuff.third2resttemplateprop.study.mapstruct;

import com.alibaba.fastjson.JSON;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 当两个对象属性不一致时，比如User对象中某个字段不存在与UserVo当中时，
 * 在编译时会有警告提示，可以在@Mapping中配置 ignore = true，
 * 当字段较多时，可以直接在@Mapper中设置unmappedTargetPolicy属性或者unmappedSourcePolicy属性
 * 为 ReportingPolicy.IGNORE即可
 *
 * @Author 喻可
 * @Date 2022/4/24 10:17
 */
@Mapper(componentModel = "spring")
public interface UserMapping extends BaseMapping<User, UserVo> {

    //这里的注解会覆盖父类的
    @Mapping(target = "gender", source = "sex")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Override
    UserVo sourceToTarget(User var1);

    @Mapping(target = "sex", source = "gender")
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Override
    User targetToSource(UserVo var1);

    /**
     * 自定义属性类型转换方法
     *
     * @param config
     * @return
     */
    default List<UserVo.UserConfig> strConfigToListUserConfig(String config) {
        return JSON.parseArray(config, UserVo.UserConfig.class);
    }

    /**
     * 自定义属性类型转换方法
     *
     * @param list
     * @return
     */
    default String listUserConfigToStrConfig(List<UserVo.UserConfig> list) {
        return JSON.toJSONString(list);
    }
}
