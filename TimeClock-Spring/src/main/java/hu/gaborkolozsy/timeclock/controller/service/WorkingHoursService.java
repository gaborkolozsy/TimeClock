/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.controller.service;

import hu.gaborkolozsy.timeclock.model.WorkingHours;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Extended {@code WorkingHoursService} interface.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see List
 */
public interface WorkingHoursService extends CommonService<WorkingHours, Long> {

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param primaryKey primary key
     * @return boolean indicating if entity is in persistence context
     */
    boolean isWorkingHoursExist(Long primaryKey);
    
    /**
     * Update the {@link WorkingHours}' {@code Work_End} column from null to the
     * correct time.
     * @param workingHours {@code WorkingHours}
     * @param workEnd the work's end
     */
    void updateWorkEnd(WorkingHours workingHours, LocalDateTime workEnd);
    
}
