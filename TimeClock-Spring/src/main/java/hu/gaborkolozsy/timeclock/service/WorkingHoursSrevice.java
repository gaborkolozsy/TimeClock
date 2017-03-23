/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service;

import hu.gaborkolozsy.timeclock.dao.WorkingHoursDAO;
import hu.gaborkolozsy.timeclock.dao.WorkingHoursDAOImpl;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.model.WorkingHours.WorkingHoursBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Work time service. Connect between Controller and DAO.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see WorkingHoursDAOImpl
 * @see WorkingHours
 * @see LocalDate
 * @see LocalDateTime
 * @see Autowired
 * @see Component
 * @see Transactional
 */
@Component
@Transactional
public class WorkingHoursSrevice {

    @Autowired
    WorkingHoursDAO workingHoursDao;
    
    public void add() {
        workingHoursDao.add(createWorkingHours());
    }
    
    public void update() {
        workingHoursDao.update(createWorkingHours());
    }
    
    public void updateDeveloperId(int oldDeveloperId, int newDeveloperId) {
        workingHoursDao.updateDeveloperIdBySpecifiedId(oldDeveloperId, newDeveloperId);
    }
    
    public WorkingHours findByDeveloperId(int developerId) {
        return workingHoursDao.get(developerId);
    }
    
    private WorkingHours createWorkingHours() {
        return WorkingHoursBuilder.create()
                .setDeveloperId(2)
                .setDay(LocalDate.now())
                .setWorkStart(LocalDateTime.now())
                .setWorkEnd(LocalDateTime.now().plusHours(8))
                .build();               
    }
    
}
