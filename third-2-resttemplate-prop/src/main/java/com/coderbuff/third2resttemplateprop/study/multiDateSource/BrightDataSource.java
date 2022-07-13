package com.coderbuff.third2resttemplateprop.study.multiDateSource;//package com.coderbuff.third2resttemplateprop.study.multiDateSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//
///**
// * bright数据源  tddl方式配置
// * @Author 喻可
// * @Date 2022/6/28 17:51
// */
//@Configuration
//public class BrightDataSource {
//
//    @Bean(name = "genBrightDataSource", initMethod = "init", destroyMethod = "destroy")
//    public DataSource genBrightDataSource() {
//        return TGroupDataSourceBuilder.create()
//                .appName("BRIGHT_APP")
//                .dbGroupKey("BRIGHT_GROUP")
//                .build();
//    }
//
//
//    /**
//     * 多数据源需要配置各自的事务
//     * @param prodDataSource
//     * @return
//     */
//    @Bean
//    public PlatformTransactionManager brightTransactionManager(@Qualifier("genBrightDataSource") DataSource prodDataSource) {
//        return new DataSourceTransactionManager(prodDataSource);
//    }
//
//
//}
