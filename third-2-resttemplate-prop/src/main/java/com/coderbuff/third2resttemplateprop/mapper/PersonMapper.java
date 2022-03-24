package com.coderbuff.third2resttemplateprop.mapper;

import com.coderbuff.third2resttemplateprop.entity.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 喻可
 * @Date 2022/3/24 10:03
 */
@Mapper
public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}