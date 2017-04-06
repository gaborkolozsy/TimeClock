/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.controller.dao.impl;

import hu.gaborkolozsy.timeclock.controller.dao.DeveloperDAO;
import hu.gaborkolozsy.timeclock.model.Developer;
import hu.gaborkolozsy.timeclock.model.Developer.DeveloperBuilder;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Extends {@code CommonDAOImpl} and implement {@code DeveloperDAO}. 
 * Interact with persistence context (database).
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see DeveloperBuilder
 * @see list
 */
@Repository
public class DeveloperDAOImpl extends CommonDAOImpl<Developer, Long> implements DeveloperDAO {

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param developerId developer's ID
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isDeveloperExist(Integer developerId) {
        return entityManager.contains(getByDeveloperId(developerId));
    }

    /**
     * Returns a {@code Developer} entity instance by the specified developer's ID.
     * @param developerID developer's ID
     * @return the {@code Developer} instance
     */
    @Override
    public Developer getByDeveloperId(Integer developerID) {
        return entityManager.createNamedQuery("getByDeveloperId", Developer.class)
                .setParameter("developerId", developerID)
                .getSingleResult();
    }

    /**
     * Returns a list of the {@code Developer} entity by the specified 
     * developer's forename.
     * @param forename developer's forename
     * @return a list of {@code Developer}
     */
    @Override
    public List<Developer> getAllByForename(String forename) {
        return entityManager.createNamedQuery("getAllByForename", Developer.class)
                .setParameter("forename", forename)
                .getResultList();
    }

    /**
     * Update the {@link Developer}'s last name by the specified develpoer's ID.
     * @param developerId developer's ID
     * @param lastname developer's last name
     */
    @Override
    public void updateLastnameByDeveloperId(Integer developerId, String lastname) {
        entityManager.merge(new DeveloperBuilder(getByDeveloperId(developerId))
                .setLastName(lastname)
                .build());
    }

    /**
     * Remove the {@link Developer} entity with specified developer's ID.
     * @param developerId developer's ID
     */
    @Override
    public void removeByDeveloperId(Integer developerId) {
        entityManager.remove(getByDeveloperId(developerId));
    }

}
