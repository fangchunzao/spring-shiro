package com.shiro.build.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author FCZ
 * @since 2019/3/1 15:22
 * 数据源配置
 */
@Configuration
public class DatabaseConfiguration {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

}
