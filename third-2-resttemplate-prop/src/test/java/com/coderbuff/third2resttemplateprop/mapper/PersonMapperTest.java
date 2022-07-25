package com.coderbuff.third2resttemplateprop.mapper;

import com.coderbuff.third2resttemplateprop.entity.Person;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @Author yuke
 * @Date 2022/7/25 9:24
 * @Description:
 */
public class PersonMapperTest {
    private static PersonMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(PersonMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/PersonMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(PersonMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectByPrimaryKey() {
        Person person = mapper.selectByPrimaryKey(1);
        System.out.println(person);
    }
}
