package com.coderbuff.third2resttemplateprop.mapper;

import com.coderbuff.third2resttemplateprop.entity.School;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 喻可
 * @Date 2022/3/24 10:03
 */
@Mapper
public interface SchoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
}