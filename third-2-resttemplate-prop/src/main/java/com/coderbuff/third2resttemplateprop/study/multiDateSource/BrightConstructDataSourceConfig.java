package com.coderbuff.third2resttemplateprop.study.multiDateSource;//package com.coderbuff.third2resttemplateprop.study.multiDateSource;
//
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.coderbuff.third2resttemplateprop.config.UpdateMetaObjectHandler;
//import org.apache.ibatis.session.SqlSessionFactory;
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
// * @Author 喻可
// * @Date 2022/6/28 17:56
// */
//
//@Configuration
//@MapperScan(basePackages = "com.aliyun.wind2.infrastructure.db.brightconstruct", sqlSessionFactoryRef = "sqlSessionFactory2", sqlSessionTemplateRef = "sqlSessionTemplate2")
//public class BrightConstructDataSourceConfig {
//
//    @Bean(name = "sqlSessionFactory2")
//    public SqlSessionFactory sqlSessionFactory2(DataSource genBrightConstructDataSource,
//                                                @Value("classpath:bright_construct-config.xml") Resource configLocation,
//                                                GlobalConfig conf,
//                                                MybatisPlusInterceptor interceptor) throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(genBrightConstructDataSource);
//        sqlSessionFactoryBean.setConfigLocation(configLocation);
//        sqlSessionFactoryBean.setGlobalConfig(conf);
//        //添加分页插件
//        sqlSessionFactoryBean.setPlugins(interceptor);
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean(name = "sqlSessionTemplate2")
//    public SqlSessionTemplate sqlSessionTemplate2(SqlSessionFactory sqlSessionFactory2) {
//        return new SqlSessionTemplate(sqlSessionFactory2);
//    }
//
//    @Bean
//    public GlobalConfig globalConfig(UpdateMetaObjectHandler updateMetaObjectHandler) {
//        GlobalConfig conf = new GlobalConfig();
//        //添加修改数据自动更改时间
//        conf.setMetaObjectHandler(updateMetaObjectHandler);
//        return conf;
//    }
//
//}
