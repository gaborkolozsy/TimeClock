/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * 
 * @see ComponentScan
 * @see Configuration
 */
@Configuration
@ComponentScan("hu.gaborkolozsy.timeclock.*")
public class ApplicationConfig {
    
}
