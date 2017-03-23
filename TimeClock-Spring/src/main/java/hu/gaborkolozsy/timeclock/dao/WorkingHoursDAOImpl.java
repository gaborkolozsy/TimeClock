/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */
package hu.gaborkolozsy.timeclock.dao;

import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.model.WorkingHours.WorkingHoursBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Interact with database.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see WorkingHours
 * @see EntityManager
 * @see PersistenceContext
 * @see TypedQuery
 * @see Component
 * @see Repository
 */
@Component
@Repository
public class WorkingHoursDAOImpl extends CrudDAOImpl<WorkingHours, Integer> implements WorkingHoursDAO {

    @Override
    public boolean isWorkingHoursExist(Integer key) {
        return entityManager.contains(get(key));
    }
    
    @Override
    public Iterable<WorkingHours> getAllByDeveloperId(Integer developerId) {
        TypedQuery<WorkingHours> query = entityManager
                .createQuery("select w from Working_Hours w "
                           + "where w.developerId = :developerId", WorkingHours.class);
        query.setParameter("developerId", developerId);
        return query.getResultList();
    }
    
    @Override
    public void updateDeveloperIdBySpecifiedId(Integer oldDeveloperId, Integer newDeveloperId) {
        getAllByDeveloperId(oldDeveloperId).forEach(w -> entityManager
                                           .merge(new WorkingHoursBuilder(w)
                                           .setDeveloperId(newDeveloperId)
                                           .build()));
    }

    @Override
    public void removeAllWorkingHoursByDeveloperId(Integer developerId) {
        getAllByDeveloperId(developerId).forEach(w -> entityManager.remove(w));
    }
    
}
