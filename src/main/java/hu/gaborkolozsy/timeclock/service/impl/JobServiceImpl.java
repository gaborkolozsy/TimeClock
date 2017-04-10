/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.dao.CrudDAO;
import hu.gaborkolozsy.timeclock.dao.JobDAO;
import hu.gaborkolozsy.timeclock.dao.impl.CrudDAOImpl;
import hu.gaborkolozsy.timeclock.model.Job;
import hu.gaborkolozsy.timeclock.service.JobService;
import java.util.List;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Job service implementation. Connect between Controller and DAO.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see CrudDAO
 * @see JobDAO
 * @see CrudDAOImpl
 * @see JobDAOImpl
 * @see List
 * @see Autowired
 * @see Qualifier
 */
@Service
public class JobServiceImpl extends CommonServiceImpl<Job, Long> implements JobService {

    @Autowired
    private final JobDAO jobDAO;

    /**
     * Constructor in parameter wait a {@link CrudDAOImpl} instance with its
     * interface type.
     * 
     * <p><strong>
     * If the parameter type implement an interface than must use it as a parameter type. 
     * Otherwise throw an {@link NoSuchBeanDefinitionException} exception!!!
     * </strong>
     * 
     * @param crudDao {@link JobDAOImpl} instance 
     */
    public JobServiceImpl(@Qualifier("jobDAOImpl") CrudDAO<Job, Long> crudDao) {
        super(crudDao);
        this.jobDAO = (JobDAO) crudDao;
    }

    /**
     * Returns a {@code Job} entity by the specified order No..
     * @param orderNumber job's order No.
     * @return a {@code Job} instance
     */
    @Override
    public Job getByOrderNumber(int orderNumber) {
        return jobDAO.getByOrderNumber(orderNumber);
    }

    /**
     * Returns a list of the {@code Job} entities by the specified project name.
     * @param projectName project's name
     * @return a list of {@code Job}
     */
    @Override
    public List<Job> getAllByProjectName(String projectName) {
        return jobDAO.getAllByProjectName(projectName);
    }

    /**
     * Returns a list of the {@code Job} entities by the specified status.
     * @param status project's status
     * @return a list of {@code Job}
     */
    @Override
    public List<Job> getAllByStatus(String status) {
        return jobDAO.getAllByStatus(status);
    }

    /**
     * Update {@link Job}'s status by specified order No..
     * @param orderNumber job's order No.
     * @param status the job's status
     */
    @Override
    public void updateStatusByOrderNumber(int orderNumber, String status) {
        jobDAO.updateStatusByOrderNumber(orderNumber, status);
    }

    /**
     * Update {@link Job}'s comment by specified order No..
     * @param orderNumber job's order No.
     * @param comment the job's comment
     */
    @Override
    public void updateCommentByOrderNumber(int orderNumber, String comment) {
        jobDAO.updateCommentByOrderNumber(orderNumber, comment);
    }

    /**
     * Remove {@link Job} entity with specified order No..
     * @param orderNumber job's order No.
     */
    @Override
    public void removeByOrderNumber(int orderNumber) {
        jobDAO.removeByOrderNumber(orderNumber);
    }

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param orderNumber job's order No.
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isJobExist(int orderNumber) {
        return jobDAO.isJobExist(orderNumber);
    }
    
}
