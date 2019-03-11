package com.suchenghe.dao.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author SuChenghe
 * @date 2018/12/7 17:37
 */
@Configuration
@MapperScan(basePackages = "com.suchenghe.dao.mysql.datasourcemappersecond", sqlSessionTemplateRef = "mysqlSqlSessionTemplate2")
public class MysqlSqlSession2 {

    @Bean(name = "mysqlSqlSessionFactory2")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("mysqlDataSource2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/datasource2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "mysqlTransactionManager2")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("mysqlDataSource2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate2")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
