/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.DevelopmentTest;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.service.WorkingHoursService;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test {@code WorkingHoursServiceImpl} class and part of {@code WorkingHours} data 
 * access layer through with it.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
public class WorkingHoursServiceImplTest extends DevelopmentTest {

    @Autowired
    private WorkingHoursService workingHoursService;
    
    /**
     * Test services that not null.
     */
    @Test
    public void testServicesNotNull() {
        assertNotNull(workingHoursService);
    }
    
    /**
     * Test of updateWorkEnd method, of class WorkingHoursServiceImpl.
     */
    @Test
    public void testUpdateWorkEnd() {
        List<WorkingHours> list = (List<WorkingHours>) workingHoursService.getAll();
        for (int i = 0; i < Math.min(9, list.size()); i++) {
            WorkingHours wh = list.get(i);
            assertNotNull(ISNULL, wh);
            assertNull("The \"Work_End\" column is not null!", wh.getWorkEnd());
            assertNull("Already updated!", wh.getAudit().getUpdated());
            message = "Version is not 0!";
            assertEquals(message, 0, wh.getVersion());
            
            LocalDateTime ldt = LocalDateTime.parse("2017-04-17T0" + i + ":00:00");
            workingHoursService.updateWorkEnd(wh, ldt);
        }
        
        list = (List<WorkingHours>) workingHoursService.getAll();
        for (int i = 0; i < Math.min(9, list.size()); i++) {            
            WorkingHours wh = workingHoursService.get(list.get(i).getId());
            assertNotNull(ISNULL, wh);
            assertNotNull("The \"Work_End\" column is null!", wh.getWorkEnd());
            assertNotNull("Still not updated!", wh.getAudit().getUpdated());
            message = "Version is still 0!";
            assertEquals(message, 1, wh.getVersion());
            
            LocalDateTime ldt = LocalDateTime.parse("2017-04-17T0" + i + ":00:00");
            message = "The \"Work_End\" column is not updated!";
            assertEquals(message, ldt, wh.getWorkEnd());
        }        
    }
    
    /**
     * Test throws exception by non exist ID.
     */
    @Test
    public void testThrowsExceptionWhenIdIsNotExist() {
        message = "Throws exception test is not ok!";
        assertEquals(message, "OK", 
                new ExceptionVerifier(() -> workingHoursService.get(INVALID))
                .isThrowing(NullPointerException.class));
    }

}