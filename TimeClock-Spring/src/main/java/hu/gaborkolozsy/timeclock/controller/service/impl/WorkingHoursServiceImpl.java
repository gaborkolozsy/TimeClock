/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.controller.service.impl;

import hu.gaborkolozsy.timeclock.controller.dao.CommonDAO;
import hu.gaborkolozsy.timeclock.controller.dao.WorkingHoursDAO;
import hu.gaborkolozsy.timeclock.controller.dao.impl.CommonDAOImpl;
import hu.gaborkolozsy.timeclock.controller.dao.impl.WorkingHoursDAOImpl;
import hu.gaborkolozsy.timeclock.controller.service.WorkingHoursService;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import java.time.LocalDateTime;
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
 * @see LocalDateTime
 * @see List
 * @see Autowired
 * @see Qualifier
 */
@Service
@Transactional
public class WorkingHoursServiceImpl extends CommonServiceImpl<WorkingHours, Long> implements WorkingHoursService {

    @Autowired
    private final WorkingHoursDAO workingHoursDao;
    
    /**
     * Constructor in parameter wait a {@link CommonDAOImpl} instance with its
     * interface type.
     * 
     * <p><strong>
     * If the parameter type implement an interface than must use it as a parameter type. 
     * Otherwise throw an {@link NoSuchBeanDefinitionException} exception!!!
     * </strong>
     * 
     * @param commonDao {@link WorkingHoursDAOImpl} instance 
     */
    public WorkingHoursServiceImpl(@Qualifier("workingHoursDAOImpl") CommonDAO<WorkingHours, Long> commonDao) {
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
    public boolean isWorkingHoursExist(Long primaryKey) {
        return workingHoursDao.isWorkingHoursExist(primaryKey);
    }

    /**
     * Update the {@link WorkingHours}' {@code Work_End} column from null to the
     * correct time.
     * @param workingHours {@code WorkingHours}
     * @param workEnd the work's end
     */
    @Override
    public void updateWorkEnd(WorkingHours workingHours, LocalDateTime workEnd) {
        workingHoursDao.updateWorkEnd(workingHours, workEnd);
    }
    
}
