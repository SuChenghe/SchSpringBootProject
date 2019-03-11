package com.suchenghe.dao.mysql.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author SuChenghe
 * @date 2018/12/5 17:13
 */
@Component
@Setter
@Getter
@PropertySource("classpath:mybatis/datasource/mysql2.properties")
@ConfigurationProperties(prefix = "spring.datasource.myspring2")
public class MysqlHikariDataSource2 {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private int maxPoolSize;
    private int minIdle;
    private String poolName;

    @Bean(name = "mysqlDataSource2")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        config.setMaximumPoolSize(maxPoolSize);
        config.setMinimumIdle(minIdle);
        config.setPoolName(poolName);

        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
