/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.controller.service.impl;

import hu.gaborkolozsy.timeclock.DevelopmentTest;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.service.DeveloperService;
import hu.gaborkolozsy.timeclock.service.WorkingHoursService;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@code WorkingHours} data access layer test from {@code WorkingHoursServiceImpl} 
 * class.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
public class WorkingHoursServiceImplTest extends DevelopmentTest {

    @Autowired
    private WorkingHoursService workingHoursService;
    
    @Autowired
    private DeveloperService developerService;
    
    /**
     * Test services that not null.
     */
    @Test
    public void testServicesNotNull() {
        assertNotNull(workingHoursService);
        assertNotNull(developerService);
    }
    
    /**
     * Test of updateWorkEnd method, of class WorkingHoursServiceImpl.
     */
    @Test
    public void testUpdateWorkEnd() {
        List<WorkingHours> list = (List<WorkingHours>) workingHoursService.getAll();
        for (int i = 0; i < Math.min(9, list.size()); i++) {
            LocalDateTime ldt = LocalDateTime.parse("2017-04-17T0" + i + ":00:00");
            WorkingHours wh = list.get(i);
            assertNotNull("WorkingHours instance is null!", wh);
            assertNull("The \"Work_End\" column is not null!", wh.getWorkEnd());
            assertNull("Already updated!", wh.getAudit().getUpdated());
            assertEquals(0, wh.getVersion());
            workingHoursService.updateWorkEnd(wh, ldt);
        }
        
        list = (List<WorkingHours>) workingHoursService.getAll();
        for (int i = 0; i < Math.min(9, list.size()); i++) {
            LocalDateTime ldt = LocalDateTime.parse("2017-04-17T0" + i + ":00:00");
            WorkingHours wh = workingHoursService.get(list.get(i).getId());
            assertNotNull("WorkingHours instance is null!", wh);
            assertNotNull("The \"Work_End\" column is null!", wh.getWorkEnd());
            assertNotNull("Still not updated!", wh.getAudit().getUpdated());
            assertEquals(1, wh.getVersion());
            assertEquals(ldt, wh.getWorkEnd());
        }
        
    }

}