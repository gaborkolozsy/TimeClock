/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Integration test JDBC {@link javax.sql.DataSource} configuration.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see EntityManager
 * @see EntityManagerFactory
 * @see PersistenceContext
 * @see Bean
 * @see EmbeddedDatabaseBuilder
 * @see EmbeddedDatabaseType
 */
@Configuration
@Profile("development")
public class TestDataSourceConfig extends DataSourceConfig {

    /**
     * Create {@code EntityManager} to persistence context.
     * @param emf {@link EntityManagerFactory}
     * @return entity manager instance
     */
    @Bean
    @PersistenceContext
    public EntityManager createEntityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }
 
    /**
     * Embedded H2 database.
     * 
     * <p>An {@code EmbeddedDatabase} is also a {@code DataSource} and adds a
     * {@code #shutdown} operation so that the embedded database instance can be
     * shut down gracefully. Testdb is the default name too.
     * @return the embedded database as a {@code DataSource}
     */
    @Bean
    @Override
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb")
                //.addScript("sql/create.sql")
                //.addScript("sql/import.sql")
                .build();
    }
    
}
