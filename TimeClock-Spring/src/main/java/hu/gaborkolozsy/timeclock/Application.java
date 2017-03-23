/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 * 
 */
package hu.gaborkolozsy.timeclock;

import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.service.WorkingHoursSrevice;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
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
            
            WorkingHoursSrevice workTimeService = context.getBean(WorkingHoursSrevice.class);
            //workTimeService.add();
            workTimeService.update();
            
            workTimeService.updateDeveloperId(1, 3);
            
            WorkingHours workTime = workTimeService.findByDeveloperId(3);
            System.out.println(workTime.getDeveloperId());
            System.out.println(workTime.getDay());
            System.out.println(workTime.getWorkStart());
            System.out.println(workTime.getWorkEnd());
            System.out.println(workTime.getAudit().getUpdatedBy());
            
            LocalDateTime end = workTime.getWorkEnd();
            LocalDateTime start = workTime.getWorkStart();
            System.out.println(LocalTime.ofSecondOfDay(end.toEpochSecond(ZoneOffset.UTC) - 
                                                       start.toEpochSecond(ZoneOffset.UTC)));
        }
    }
    
}
