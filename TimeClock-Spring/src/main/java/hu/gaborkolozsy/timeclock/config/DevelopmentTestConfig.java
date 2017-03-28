/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Integration test JDBC {@link javax.sql.DataSource} configuration.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
@Configuration
@Profile("development")
@PropertySource("classpath:properties/test.properties")
public class DevelopmentTestConfig extends Config {

    @Bean
    @PersistenceContext
    public EntityManager createEntityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }
    
}
