/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service;

import hu.gaborkolozsy.timeclock.model.WorkingHours;
import java.time.LocalDateTime;

/**
 * Extended {@code WorkingHoursService} interface.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see LocalDateTime
 */
public interface WorkingHoursService extends CrudService<WorkingHours, Long> {

    /**
     * Update the {@link WorkingHours}' {@code Work_End} column from null to the
     * correct time.
     * @param workingHours {@code WorkingHours}
     * @param workEnd the work's end
     */
    void updateWorkEnd(WorkingHours workingHours, LocalDateTime workEnd);
    
}
