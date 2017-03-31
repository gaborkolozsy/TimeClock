/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */
package hu.gaborkolozsy.timeclock.controller.dao.impl;

import hu.gaborkolozsy.timeclock.controller.dao.WorkingHoursDAO;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.model.WorkingHours.WorkingHoursBuilder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/** 
 * Extends {@code CrudeDAOImpl} and implement {@code WorkingHoursDAO}. 
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
public class WorkingHoursDAOImpl extends CommonDAOImpl<WorkingHours, Integer> implements WorkingHoursDAO {

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param primaryKey primary key
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isWorkingHoursExist(Integer primaryKey) {
        return entityManager.contains(get(primaryKey));
    }
    
    /**
     * Returns a list of the {@link WorkingHours} entity 
     * by the specified {@code developerId} foreign key.
     * @param developerId developer ID
     * @return a list of {@code WorkingHours} 
     */
    @Override
    public List<WorkingHours> getAllByDeveloperId(Integer developerId) {
        TypedQuery<WorkingHours> query = entityManager
                .createQuery("select w from Working_Hours w "
                           + "where w.developerId = :developerId", WorkingHours.class)
                .setParameter("developerId", developerId);
        return query.getResultList();
    }
    
    /**
     * Update all {@link WorkingHours}' {@code developerId} by the specified 
     * foreign key.
     * @param oldDeveloperId the old developer ID
     * @param newDeveloperId the new developer ID
     */
    @Override
    public void updateAllDeveloperIdBySpecifiedId(Integer oldDeveloperId, Integer newDeveloperId) {
        getAllByDeveloperId(oldDeveloperId).forEach(w -> entityManager
                                           .merge(new WorkingHoursBuilder(w)
                                           .setDeveloperId(newDeveloperId)
                                           .build()));
    }

    /**
     * Remove all {@link WorkingHours} entity with specified {@code developerId}
     * foreign key.
     * @param developerId developer ID
     */
    @Override
    public void removeAllWorkingHoursByDeveloperId(Integer developerId) {
        getAllByDeveloperId(developerId).forEach(w -> entityManager.remove(w));
    }
    
}
