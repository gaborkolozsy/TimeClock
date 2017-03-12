/*
 * Copyright (c) 2017, Gábor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration.
 * 
 * @author Kolozsy Gábor (kolozsygabor@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * 
 * @see ComponentScan
 * @see Configuration
 */
@Configuration
@ComponentScan("hu.gaborkolozsy.timeclock.*")
public class ApplicationConfig {
    
}
