package com.suchenghe.dao.mysql.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
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
@PropertySource("classpath:dao/datasource/mysql1.properties")
@ConfigurationProperties(prefix = "spring.datasource.myspring1")
public class MysqlHikariDataSource1 {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private boolean autoCommit;
    private String poolName;
    private int maxPoolSize;
    private int minIdle;
    private long connectionTimeout;
    private long maxLifetime;
    private long validationTimeout;
    private long idleTimeout;

    @Primary
    @Bean(name = "mysqlDataSource1")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        config.setAutoCommit(autoCommit);
        config.setPoolName(poolName);
        config.setMaximumPoolSize(maxPoolSize);
        config.setMinimumIdle(minIdle);
        config.setConnectionTimeout(connectionTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setValidationTimeout(validationTimeout);
        config.setIdleTimeout(idleTimeout);

        //启用高速缓存，为true时下面两个参数才生效，您必须将此参数设置为true
        config.addDataSourceProperty("cachePrepStmts", "true");
        //连接池大小默认25，官方推荐250-500
        config.addDataSourceProperty("prepStmtCacheSize", "25");
        //单条语句最大长度默认256，官方推荐2048
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        //较新版本的MySQL支持服务器端预处理语句，这可以提供显着的性能提升。将此属性设置为true
        config.addDataSourceProperty("useServerPrepStmts", "true");
        //驱动程序是否应该引用由协议维护的Connection.setAutoCommit（）和Connection.setTransactionIsolation（）以及事务状态设置的自动提交和事务隔离的内部值，
        //而不是查询数据库或盲目地向数据库发送命令commit（）或rollback（）方法调用,默认值:false
        config.addDataSourceProperty("useLocalSessionState", "true");
        //批量插入
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        //驱动程序应该为Statements和PreparedStatements缓存ResultSetMetaData吗？（要求JDK-1.4 +，真/假，默认'假'）
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
