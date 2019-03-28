package com.suchenghe.dao.mysql.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author SuChenghe
 * @date 2019/03/28 10:00
 */
@Configuration("schRepositoryConfig")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.suchenghe.dao.mysql.jpa.dao",
        entityManagerFactoryRef = "schEntityManagerFactory",
        transactionManagerRef = "schTransactionManager")
public class SchRepositoryConfig {

    @Autowired
    @Qualifier("mysqlDataSource1")
    private DataSource mysqlDataSource1;
    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "schTransactionManager")
    PlatformTransactionManager nbugsCourseTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

    @Bean(name = "schEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "schEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(mysqlDataSource1)
                .properties(getVendorProperties())
                //扫描@Entity注释的类
                .packages("com.suchenghe.dao.mysql.jpa.pojo")
                .build();
    }

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

}
