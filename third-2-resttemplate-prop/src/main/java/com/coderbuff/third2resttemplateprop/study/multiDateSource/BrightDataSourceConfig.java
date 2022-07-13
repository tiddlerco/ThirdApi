package com.coderbuff.third2resttemplateprop.study.multiDateSource;//package com.coderbuff.third2resttemplateprop.study.multiDateSource;
//
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//
//import javax.sql.DataSource;
//
///**
// * bright数据源  tddl方式配置
// * @Author 喻可
// * @Date 2022/6/28 17:51
// */
//@Configuration
//@MapperScan(basePackages = "com.aliyun.wind2.infrastructure.db.bright", sqlSessionFactoryRef = "sqlSessionFactory1", sqlSessionTemplateRef = "sqlSessionTemplate1")
//public class BrightDataSourceConfig {
//    @Bean(name = "sqlSessionFactory1")
//    public SqlSessionFactory sqlSessionFactory1(DataSource genBrightDataSource,
//                                                @Value("classpath:bright-config.xml") Resource configLocation) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(genBrightDataSource);
//        sqlSessionFactoryBean.setConfigLocation(configLocation);
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean(name = "sqlSessionTemplate1")
//    public SqlSessionTemplate sqlSessionTemplate1(SqlSessionFactory sqlSessionFactory1) {
//        return new SqlSessionTemplate(sqlSessionFactory1);
//    }
//}
