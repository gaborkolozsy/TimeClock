/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.dao.impl;

import hu.gaborkolozsy.timeclock.dao.JobDao;
import hu.gaborkolozsy.timeclock.model.Job;
import hu.gaborkolozsy.timeclock.model.Job.JobBuilder;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Extends {@code CrudDaoImpl} and implement {@code JobDao}. 
 * Interact with persistence context (database).
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see JobBuilder
 * @see List
 */
@Repository
public class JobDaoImpl extends CrudDaoImpl<Job, Long> implements JobDao {

    /**
     * Returns a {@code Job} entity by the specified order No..
     * @param orderNumber job's order No.
     * @return a {@code Job} instance
     */
    @Override
    public Job getByOrderNumber(int orderNumber) {
        return entityManager.createNamedQuery("getAllByOrderNumber", Job.class)
                .setParameter("orderNumber", orderNumber)
                .getSingleResult();
    }

    /**
     * Returns a list of the {@code Job} entities by the specified project name.
     * @param projectName project's name
     * @return a list of {@code Job}
     */
    @Override
    public List<Job> getAllByProjectName(String projectName) {
        return entityManager.createNamedQuery("getAllByProjectName", Job.class)
                .setParameter("projectName", projectName)
                .getResultList();
    }

    /**
     * Returns a list of the {@code Job} entities by the specified status.
     * @param status project's status
     * @return a list of {@code Job}
     */
    @Override
    public List<Job> getAllByStatus(String status) {
        return entityManager.createNamedQuery("getAllByStatus", Job.class)
                .setParameter("status", status)
                .getResultList();
    }

    /**
     * Update {@link Job}'s status by specified order No..
     * @param orderNumber job's order No.
     * @param status the job's status
     */
    @Override
    public void updateStatusByOrderNumber(int orderNumber, String status) {
        entityManager.merge(new JobBuilder(getByOrderNumber(orderNumber))
                .setStatus(status)
                .build());
    }

    /**
     * Update {@link Job}'s comment by specified order No..
     * @param orderNumber job's order No.
     * @param comment the job's comment
     */
    @Override
    public void updateCommentByOrderNumber(int orderNumber, String comment) {
        entityManager.merge(new JobBuilder(getByOrderNumber(orderNumber))
                .setComment(comment)
                .build());
    }

    /**
     * Remove {@link Job} entity with specified order No..
     * @param orderNumber job's order No.
     */
    @Override
    public void removeByOrderNumber(int orderNumber) {
        entityManager.remove(getByOrderNumber(orderNumber));
    }

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param orderNumber job's order No.
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isJobExist(int orderNumber) {
        return getByOrderNumber(orderNumber) != null;
    }
    
}
