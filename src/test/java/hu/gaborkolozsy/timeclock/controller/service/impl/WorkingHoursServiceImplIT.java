/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.controller.service.impl;

import hu.gaborkolozsy.timeclock.DevelopmentTest;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.model.WorkingHours.WorkingHoursBuilder;
import hu.gaborkolozsy.timeclock.service.WorkingHoursService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Not working.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkingHoursServiceImplIT extends DevelopmentTest {

    @Autowired
    private WorkingHoursService workingHoursService;
    
    /**
     * Test is workingHoursService correct instance.
     */
    @Test
    public void testCorrectType() {
        assertThat(workingHoursService, instanceOf(WorkingHoursService.class));
    }
    
    /**
     * Test of updateWorkEnd method, of class WorkingHoursServiceImpl.
     */
    @Test
    public void testUpdateWorkEnd() {
        assertNotNull(createWorkingHours());
    }
    
    private static WorkingHours createWorkingHours() {
        return new WorkingHoursBuilder()
                .setDay(LocalDate.now())
                .setWorkStart(LocalDateTime.now())
                .build();               
    }

}