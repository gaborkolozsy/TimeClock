/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 * 
 */
package hu.gaborkolozsy.timeclock;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Maven based application with <strong>Spring, JPA</strong> and 
 * <strong>Hibernate</strong> as a <strong>JPA</strong> implementation.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @version 0.0.1-SNAPSHOT
 * @see ConfigurableApplicationContext
 * @see AnnotationConfigApplicationContext
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = 
                new AnnotationConfigApplicationContext("hu.gaborkolozsy.timeclock.config")) {
            
        }
        
    }
    
}
