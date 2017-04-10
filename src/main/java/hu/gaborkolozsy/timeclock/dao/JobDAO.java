/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.dao;

import hu.gaborkolozsy.timeclock.model.Job;
import java.util.List;

/**
 * Extended {@code JobDAO} interface.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see List
 */
public interface JobDAO extends CrudDAO<Job, Long> {

    /**
     * Returns a {@code Job} entity by the specified order No..
     * @param orderNumber job's order No.
     * @return a {@code Job} instance
     */
    Job getByOrderNumber(int orderNumber);
    
    /**
     * Returns a list of the {@code Job} entities by the specified project name.
     * @param projectName project's name
     * @return a list of {@code Job}
     */
    List<Job> getAllByProjectName(String projectName);
    
    /**
     * Returns a list of the {@code Job} entities by the specified status.
     * @param status project's status
     * @return a list of {@code Job}
     */
    List<Job> getAllByStatus(String status);
    
    /**
     * Update {@link Job}'s status by specified order No..
     * @param orderNumber job's order No.
     * @param status the job's status
     */
    void updateStatusByOrderNumber(int orderNumber, String status);
    
    /**
     * Update {@link Job}'s comment by specified order No..
     * @param orderNumber job's order No.
     * @param comment the job's comment
     */
    void updateCommentByOrderNumber(int orderNumber, String comment);
    
    /**
     * Remove {@link Job} entity with specified order No..
     * @param orderNumber job's order No.
     */
    void removeByOrderNumber(int orderNumber);
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param orderNumber job's order No.
     * @return boolean indicating if entity is in persistence context
     */
    boolean isJobExist(int orderNumber);
    
}
