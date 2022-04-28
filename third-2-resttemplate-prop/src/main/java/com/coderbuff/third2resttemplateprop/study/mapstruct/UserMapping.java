package com.coderbuff.third2resttemplateprop.study.mapstruct;

import com.alibaba.fastjson.JSON;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
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

    //这里的注解会覆盖父类的,父类的注解不能继承
    @Mapping(target = "gender", source = "sex")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Override
    UserVo sourceToTarget(User var1);


    /**
     * 反向，映射同名属性
     * InheritInverseConfiguration注解 反转sourceToTarget方法的映射
     * 如果父类配置InheritInverseConfiguration注解,子类不配置不会生效
     */
    @InheritInverseConfiguration(name = "sourceToTarget")
    @Override
    User targetToSource(UserVo var1);


    /**
     * 映射同名属性，集合形式
     * InheritConfiguration注解 使用已有的映射更新对象属性
     */
    @InheritConfiguration(name = "sourceToTarget")
    List<UserVo> sourceListToTargetList(List<User> var1);


    /**
     * 反向，映射同名属性，集合形式
     */
    @InheritConfiguration(name = "targetToSource")
    List<User> targetListToSourceList(List<UserVo> var1);


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
