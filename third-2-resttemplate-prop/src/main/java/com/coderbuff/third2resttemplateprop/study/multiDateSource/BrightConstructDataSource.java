package com.coderbuff.third2resttemplateprop.study.multiDateSource;//package com.coderbuff.third2resttemplateprop.study.multiDateSource;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * bright_construct数据源
// *
// * @Author 喻可
// * @Date 2022/6/28 17:51
// */
//
//@Configuration
//public class BrightConstructDataSource {
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String userName;
//
//    @Value("${spring.datasource.password}")
//    private String passWord;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    @Bean(name = "genBrightConstructDataSource", initMethod = "init")
//    public DataSource genBrightConstructDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setUrl(url);
//        druidDataSource.setUsername(userName);
//        druidDataSource.setPassword(passWord);
//        druidDataSource.setDriverClassName(driverClassName);
//        return druidDataSource;
//    }
//
//    /**
//     * 多数据源需要配置各自的事务
//     *
//     * @param prodDataSource
//     * @return
//     */
//    @Bean
//    public PlatformTransactionManager brightConstructTransactionManager(@Qualifier("genBrightConstructDataSource") DataSource prodDataSource) {
//        return new DataSourceTransactionManager(prodDataSource);
//    }
//
//
//}
