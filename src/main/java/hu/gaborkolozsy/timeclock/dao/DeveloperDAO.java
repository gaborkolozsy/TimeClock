/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.dao;

import hu.gaborkolozsy.timeclock.model.Developer;
import java.util.List;

/**
 * Extended {@code DeveloperDAO} interface.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see List
 */
public interface DeveloperDAO extends CrudDAO<Developer, Long> {

    /**
     * Returns a {@code Developer} entity by the specified developer's ID.
     * @param developerID developer's ID
     * @return a {@code Developer} instance
     */
    Developer getByDeveloperId(Integer developerID);
    
    /**
     * Returns a list of the {@code Developer} entities by the specified fore name.
     * @param foreName fore Name
     * @return a list of {@code Developer}
     */
    List<Developer> getAllByForename(String foreName);
    
    /**
     * Update lastname by {@link Developer}'s {@code developerId}.
     * @param developerId the developer's ID
     * @param lastname the developer's lastname
     */
    void updateLastnameByDeveloperId(Integer developerId, String lastname);
    
    /**
     * Remove {@link Developer} entity with specified {@code developerId}.
     * @param developerId developer's ID
     */
    void removeByDeveloperId(Integer developerId);
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param developerId developer's ID
     * @return boolean indicating if entity is in persistence context
     */
    boolean isDeveloperExist(Integer developerId);
    
}
