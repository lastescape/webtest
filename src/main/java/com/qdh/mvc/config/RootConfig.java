package com.qdh.mvc.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by qdh on 2018/4/28.
 */
@Configuration
@ComponentScan(basePackages={"com.qdh.mvc"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@EnableTransactionManagement()
public class RootConfig {
    @Bean(value = "dataSource")
    public ComboPooledDataSource myDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://my-sql:3306/eshop?characterEncoding=UTF-8");
            dataSource.setUser("root");
            dataSource.setPassword("123456");
            dataSource.setMaxPoolSize(40);
            dataSource.setMinPoolSize(1);
            dataSource.setInitialPoolSize(1);
            dataSource.setMaxIdleTime(20);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean slf = new LocalSessionFactoryBean();
        slf.setDataSource(dataSource);
        slf.setPackagesToScan(new String[] {"com.qdh.mvc.db"});
        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        slf.setHibernateProperties(props);
        return slf;
    }

    //txManager事务开启
    @Bean
    public HibernateTransactionManager txManager(DataSource dataSource) throws SQLException {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory(dataSource).getObject());
        return hibernateTransactionManager;
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
