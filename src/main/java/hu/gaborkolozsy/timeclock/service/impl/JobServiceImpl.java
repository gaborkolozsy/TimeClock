/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.dao.CrudDao;
import hu.gaborkolozsy.timeclock.dao.JobDao;
import hu.gaborkolozsy.timeclock.dao.impl.CrudDaoImpl;
import hu.gaborkolozsy.timeclock.model.Job;
import hu.gaborkolozsy.timeclock.service.JobService;
import java.util.List;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Job service implementation. Connect between Controller and Dao.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see CrudDao
 * @see JobDao
 * @see CrudDaoImpl
 * @see JobDAOImpl
 * @see List
 * @see Autowired
 * @see Qualifier
 */
@Service
public class JobServiceImpl extends CrudServiceImpl<Job, Long> implements JobService {

    @Autowired
    private final JobDao jobDao;

    /**
     * Constructor in parameter wait a {@link CrudDaoImpl} instance with its
     * interface type.
     * 
     * <p><strong>
     * If the parameter type implement an interface than must use it as a parameter type. 
     * Otherwise throw an {@link NoSuchBeanDefinitionException} exception!!!
     * </strong>
     * 
     * @param crudDao {@link JobDAOImpl} instance 
     */
    public JobServiceImpl(@Qualifier("jobDaoImpl") CrudDao<Job, Long> crudDao) {
        super(crudDao);
        this.jobDao = (JobDao) crudDao;
    }

    /**
     * Returns a {@code Job} entity by the specified order No..
     * @param orderNumber job's order No.
     * @return a {@code Job} instance
     */
    @Override
    public Job getByOrderNumber(Long orderNumber) {
        return jobDao.getByOrderNumber(orderNumber);
    }

    /**
     * Returns a list of the {@code Job} entities by the specified project name.
     * @param projectName project's name
     * @return a list of {@code Job}
     */
    @Override
    public List<Job> getAllByProjectName(String projectName) {
        return jobDao.getAllByProjectName(projectName);
    }

    /**
     * Returns a list of the {@code Job} entities by the specified status.
     * @param status project's status
     * @return a list of {@code Job}
     */
    @Override
    public List<Job> getAllByStatus(String status) {
        return jobDao.getAllByStatus(status);
    }

    /**
     * Update {@link Job}'s status by specified order No..
     * @param orderNumber job's order No.
     * @param status the job's status
     */
    @Override
    public void updateStatusByOrderNumber(Long orderNumber, String status) {
        jobDao.updateStatusByOrderNumber(orderNumber, status);
    }

    /**
     * Update {@link Job}'s comment by specified order No..
     * @param orderNumber job's order No.
     * @param comment the job's comment
     */
    @Override
    public void updateCommentByOrderNumber(Long orderNumber, String comment) {
        jobDao.updateCommentByOrderNumber(orderNumber, comment);
    }

    /**
     * Remove {@link Job} entity with specified order No..
     * @param orderNumber job's order No.
     */
    @Override
    public void removeByOrderNumber(Long orderNumber) {
        jobDao.removeByOrderNumber(orderNumber);
    }

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param orderNumber job's order No.
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isExistWithOrderNumber(Long orderNumber) {
        return jobDao.isExistWithOrderNumber(orderNumber);
    }
    
}
