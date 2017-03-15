/*
 * Copyright (c) 2017, Gábor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * {@code DataSource} configuration.
 * 
 * @author Kolozsy Gábor (kolozsygabor@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * 
 * @see DataSource
 * @see Value
 * @see Bean
 * @see Configuration
 * @see PropertySource
 * @see PropertySourcesPlaceholderConfigurer
 * @see DriverManagerDataSource
 */
@Configuration
@PropertySource("classpath:database.properties")
public class DataSourceConfig {

    /** The JDBC <strong>url</strong> for accessing {@code DriverManager}. */
    @Value("${database.url}")
    private String url;
    
    /** The JDBC <strong>user</strong> for accessing {@code DriverManager}. */
    @Value("${database.user}")
    private String user;
    
    /** The JDBC <strong>password</strong> for accessing {@code DriverManager}. */
    @Value("${database.password}")
    private String password;
    
    /**
     * Specialization of {@link PropertySourcesPlaceholderConfigurer} that resolves<br>
     * ${...} placeholders within bean definition property values and<br>
     * {@code @Value} annotations against the current Spring<br>
     * {@link org.springframework.core.env.Environment} and its set of<br> 
     * {@link org.springframework.core.env.PropertySources}.
     * 
     * <p>This method have to run before {@code dataSource()} method and 
     * because of it must be {@code static}.
     * 
     * @return {@code PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySPC() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    /**
     * Create a new {@link DriverManagerDataSource} 
     * object with the given standard {@code DriverManager} parameters.
     * 
     * @return {@code DataSource}
     * @see DataSource
     */
    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(url, user, password);
    }
}
