/*
 * Copyright (c) 2017, Gábor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JPA configuration. Hibernate is the JPA vendor.
 *
 * @author Kolozsy Gábor (kolozsygabor@gmail.com)
 * @since 0.0.1-SNAPSHOT
 *
 * @see Properties
 * @see EntityManagerFactory
 * @see DataSource
 * @see FactoryBean
 * @see Bean
 * @see Configuration
 * @see PersistenceExceptionTranslationPostProcessor
 * @see JpaTransactionManager
 * @see JpaVendorAdapter
 * @see LocalContainerEntityManagerFactoryBean
 * @see HibernateJpaVendorAdapter
 * @see PlatformTransactionManager
 * @see EnableTransactionManagement
 */
@Configuration
@EnableTransactionManagement
public class JpaConfig {

    /**
     * {@link FactoryBean} that creates a JPA {@link EntityManagerFactory} 
     * according to JPA's standard <i>container</i> bootstrap contract.
     * 
     * @param dataSource {@link DataSource}
     * @return {@code LocalContainerEntityManagerFactoryBean}
     * @see LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(new String[] {"hu.gaborkolozsy.timeclock.entities"});
        
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.use_sql_comments", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        
        emf.setJpaProperties(properties);
        return emf;
    }
    
    /**
     * Create a new {@link JpaTransactionManager} instance.
     * 
     * @param emf {@code EntityManagerFactory}
     * @return {@code JpaTransactionManager}
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
    
    /**
     * Create a new {@link PersistenceExceptionTranslationPostProcessor} 
     * instance.
     * 
     * @return {@code PersistenceExceptionTranslationPostProcessor}
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
