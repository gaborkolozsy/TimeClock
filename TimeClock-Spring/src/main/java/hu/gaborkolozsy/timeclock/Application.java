/*
 * Copyright (c) 2017, Gábor Kolozsy. All rights reserved.
 * 
 */
package hu.gaborkolozsy.timeclock;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Maven based Time Clock application with <strong>Spring, JPA</strong> and 
 * <strong>Hibernate</strong>.
 * 
 * @author Kolozsy Gábor (kolozsygabor@gmail.com)
 * @version 0.0.1-SNAPSHOT
 * 
 * @see org.springframework.context.ConfigurableApplicationContext
 * @see org.springframework.context.annotation.AnnotationConfigApplicationContext
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
