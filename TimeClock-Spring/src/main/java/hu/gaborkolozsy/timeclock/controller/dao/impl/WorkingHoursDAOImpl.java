/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */
package hu.gaborkolozsy.timeclock.controller.dao.impl;

import hu.gaborkolozsy.timeclock.controller.dao.WorkingHoursDAO;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.model.WorkingHours.WorkingHoursBuilder;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/** 
 * Extends {@code CommonDAOImpl} and implement {@code WorkingHoursDAO}. 
 * Interact with persistence context (database).
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see WorkingHoursBuilder
 * @see EntityManager
 * @see PersistenceContext
 * @see TypedQuery
 * @see Repository
 */
@Repository
public class WorkingHoursDAOImpl extends CommonDAOImpl<WorkingHours, Long> implements WorkingHoursDAO {

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param primaryKey primary key
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isWorkingHoursExist(Long primaryKey) {
        return entityManager.contains(get(primaryKey));
    }
        
    /**
     * Update the {@link WorkingHours}' {@code Work_End} column from null to the
     * correct time.
     * @param workingHours {@code WorkingHours}
     * @param workEnd the work's end
     */
    @Override
    public void updateWorkEnd(WorkingHours workingHours, LocalDateTime workEnd) {
        entityManager.merge(new WorkingHoursBuilder(workingHours)
                .setWorkEnd(workEnd)
                .build());
    }
    
}
