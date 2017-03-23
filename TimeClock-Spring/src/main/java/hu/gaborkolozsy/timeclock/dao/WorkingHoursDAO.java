/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.dao;

import hu.gaborkolozsy.timeclock.model.WorkingHours;

/**
 * The extends {@code WorkingHoursDAO} interface.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
public interface WorkingHoursDAO extends CrudDAO<WorkingHours, Integer> {

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param key primary key
     * @return boolean indicating if entity is in persistence context
     */
    boolean isWorkingHoursExist(Integer key);
    
    /**
     * Returns a list of the {@link WorkingHours} entity by the specified
     * {@code developerId} foreign key.
     * @param developerId developer ID
     * @return a list of {@code WorkingHours} 
     */
    Iterable<WorkingHours> getAllByDeveloperId(Integer developerId);

    /**
     * Update the {@code developerId} foreign key.
     * @param oldDeveloperId the old developer ID
     * @param newDeveloperId the new developer ID
     */
    void updateDeveloperIdBySpecifiedId(Integer oldDeveloperId, Integer newDeveloperId);
    
    /**
     * Remove all {@link WorkingHours} entity with specified {@code developerId}
     * foreign key.
     * @param developerId developer ID
     */
    void removeAllWorkingHoursByDeveloperId(Integer developerId);
    
}
