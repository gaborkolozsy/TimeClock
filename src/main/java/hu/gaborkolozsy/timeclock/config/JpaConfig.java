/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see Properties
 * @see EntityManagerFactory
 * @see DataSource
 * @see FactoryBean
 * @see Value
 * @see Bean
 * @see PersistenceExceptionTranslationPostProcessor
 * @see JpaTransactionManager
 * @see JpaVendorAdapter
 * @see LocalContainerEntityManagerFactoryBean
 * @see HibernateJpaVendorAdapter
 * @see PlatformTransactionManager
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:properties/hibernate.properties")
public class JpaConfig {

    /** The <strong>hibernate.hbm2ddl.auto</strong> property for {@code Hibernate}. */
    @Value("${hibernate.hbm2ddl.auto}")
    private String createOrUpdate;
    
    /** The <strong>hibernate.show_sql</strong> property for {@code Hibernate}. */
    @Value("${hibernate.show_sql}")
    private String showSQL;
    
    /** The <strong>hibernate.format_sql</strong> property for {@code Hibernate}. */
    @Value("${hibernate.format_sql}")
    private String formatSQL;
    
    /** The <strong>hibernate.use_sql_comments</strong> property for {@code Hibernate}. */
    @Value("${hibernate.use_sql_comments}")
    private String useSQLComments;
    
    /** The <strong>hibernate.dialect</strong> property for {@code Hibernate}. */
    @Value("${hibernate.dialect}")
    private String dialect;
    
    /**
     * {@link FactoryBean} that creates a JPA {@link EntityManagerFactory} 
     * according to JPA's standard <i>container</i> bootstrap contract.
     * @param dataSource {@link DataSource}
     * @return {@code LocalContainerEntityManagerFactoryBean}
     * @see LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("Time_Clock");
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(new String[] {"hu.gaborkolozsy.timeclock.model"});
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(addHibernateProperties());
        return emf;
    }

    /**
     * Create a new {@link JpaTransactionManager} instance.
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
     * @return {@code PersistenceExceptionTranslationPostProcessor}
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    /**
     * Set the {@code Hibernate} properties.
     * @return {@code Properties}
     * @see Properties
     */
    private Properties addHibernateProperties() {
        Properties p = new Properties();
        p.setProperty("hibernate.hbm2ddl.auto", createOrUpdate);
        p.setProperty("hibernate.show_sql", showSQL);
        p.setProperty("hibernate.format_sql", formatSQL);
        p.setProperty("hibernate.use_sql_comments", useSQLComments);
        p.setProperty("hibernate.dialect", dialect);
        return p;
    }
}
