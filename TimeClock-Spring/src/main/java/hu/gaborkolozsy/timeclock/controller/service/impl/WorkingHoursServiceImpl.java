/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.controller.service.impl;

import hu.gaborkolozsy.timeclock.controller.dao.CommonDAO;
import hu.gaborkolozsy.timeclock.controller.dao.WorkingHoursDAO;
import hu.gaborkolozsy.timeclock.controller.service.WorkingHoursService;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import java.util.List;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Working hours service implementation. Connect between Controller and DAO.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see CommonDAO
 * @see WorkingHoursDAO
 * @see List
 * @see Autowired
 * @see Qualifier
 */
@Service
@Transactional
public class WorkingHoursServiceImpl extends CommonServiceImpl<WorkingHours, Integer> implements WorkingHoursService {

    @Autowired
    private final WorkingHoursDAO workingHoursDao;
    
    /**
     * Constructor wait a {@code CommonDAOImpl} instance.
     * 
     * <p><strong>
     * If the parameter type implement an interface than must use it as a parameter type. 
     * Otherwise throw an {@link NoSuchBeanDefinitionException} exception!!!
     * </strong>
     * 
     * @param commonDao 
     */
    public WorkingHoursServiceImpl(@Qualifier("workingHoursDAOImpl") CommonDAO<WorkingHours, Integer> commonDao) {
        super(commonDao);
        this.workingHoursDao = (WorkingHoursDAO) commonDao;
    }
           
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param primaryKey primary key
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isWorkingHoursExist(Integer primaryKey) {
        return workingHoursDao.isWorkingHoursExist(primaryKey);
    }

    /**
     * Returns an iterable collection of the {@link WorkingHours} entity 
     * by the specified {@code developerId} foreign key.
     * @param developerId developer ID
     * @return an iterable collection of {@code WorkingHours} 
     */
    @Override
    public List<WorkingHours> getAllByDeveloperId(Integer developerId) {
        return workingHoursDao.getAllByDeveloperId(developerId);
    }

    /**
     * Update all {@link WorkingHours}' {@code developerId} by the specified 
     * foreign key.
     * @param oldDeveloperId the old developer ID
     * @param newDeveloperId the new developer ID
     */
    @Override
    public void updateAllDeveloperIdBySpecifiedId(Integer oldDeveloperId, Integer newDeveloperId) {
         workingHoursDao.updateAllDeveloperIdBySpecifiedId(oldDeveloperId, newDeveloperId);
    }

    /**
     * Remove all {@link WorkingHours} entity with specified {@code developerId}
     * foreign key.
     * @param developerId developer ID
     */
    @Override
    public void removeAllWorkingHoursByDeveloperId(Integer developerId) {
        workingHoursDao.removeAllWorkingHoursByDeveloperId(developerId);
    }
    
}
