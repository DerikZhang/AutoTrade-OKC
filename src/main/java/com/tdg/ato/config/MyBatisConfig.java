package com.tdg.ato.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by DerikZhang on 2017/6/29.
 */
@Configuration
@MapperScan(basePackages="com.tdg.ato.mapper")
public class MyBatisConfig {
    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource getDataSource() throws Exception{
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
        props.put("url", env.getProperty("spring.datasource.url"));
        props.put("username", env.getProperty("spring.datasource.username"));
        props.put("password", env.getProperty("spring.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
        return fb.getObject();
    }

}
