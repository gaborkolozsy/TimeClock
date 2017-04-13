/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.dao.CrudDao;
import hu.gaborkolozsy.timeclock.dao.WorkingHoursDao;
import hu.gaborkolozsy.timeclock.dao.impl.CrudDaoImpl;
import hu.gaborkolozsy.timeclock.dao.impl.WorkingHoursDaoImpl;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.service.WorkingHoursService;
import java.time.LocalDateTime;
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
 * @see CrudDao
 * @see WorkingHoursDao
 * @see LocalDateTime
 * @see Autowired
 * @see Qualifier
 */
@Service
@Transactional
public class WorkingHoursServiceImpl extends CrudServiceImpl<WorkingHours, Long> 
        implements WorkingHoursService {

    @Autowired
    private final WorkingHoursDao workingHoursDao;
    
    /**
     * Constructor in parameter wait a {@link CrudDaoImpl} instance with its
     * interface type.
     * 
     * <p><strong>
     * If the parameter type implement an interface than must use it as a parameter type. 
     * Otherwise throw an {@link NoSuchBeanDefinitionException} exception!!!
     * </strong>
     * 
     * @param crudDao {@link WorkingHoursDaoImpl} instance 
     */
    public WorkingHoursServiceImpl(@Qualifier("workingHoursDaoImpl") 
            CrudDao<WorkingHours, Long> crudDao) {
        
        super(crudDao);
        this.workingHoursDao = (WorkingHoursDao) crudDao;
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
