/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */
package hu.gaborkolozsy.timeclock.dao.impl;

import hu.gaborkolozsy.timeclock.dao.WorkingHoursDAO;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.model.WorkingHours.WorkingHoursBuilder;
import java.time.LocalDateTime;
import org.springframework.stereotype.Repository;

/** 
 * Extends {@code CrudDAOImpl} and implement {@code WorkingHoursDAO}. 
 * Interact with persistence context (database).
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see WorkingHoursBuilder
 * @see LocalDateTime
 */
@Repository
public class WorkingHoursDAOImpl extends CrudDAOImpl<WorkingHours, Long> implements WorkingHoursDAO {

    /**
     * Update the {@link WorkingHours}' {@code Work_End} column from null to the
     * correct time.
     * @param workingHours {@code WorkingHours}
     * @param workEnd the working hours' end
     */
    @Override
    public void updateWorkEnd(WorkingHours workingHours, LocalDateTime workEnd) {
        entityManager.merge(new WorkingHoursBuilder(workingHours)
                .setWorkEnd(workEnd)
                .build());
    }
    
}
