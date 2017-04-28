/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.dao.CrudDao;
import hu.gaborkolozsy.timeclock.dao.DeveloperDao;
import hu.gaborkolozsy.timeclock.dao.impl.CrudDaoImpl;
import hu.gaborkolozsy.timeclock.dao.impl.DeveloperDaoImpl;
import hu.gaborkolozsy.timeclock.model.Developer;
import hu.gaborkolozsy.timeclock.service.DeveloperService;
import java.util.List;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Developer service implementation. Connect between Controller and Dao.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see CrudDao
 * @see DeveloperDao
 * @see CrudDaoImpl
 * @see DeveloperDaoImpl
 * @see List
 * @see Autowired
 * @see Qualifier
 */
@Service
public class DeveloperServiceImpl extends CrudServiceImpl<Developer, Long> 
        implements DeveloperService {

    @Autowired
    private final DeveloperDao developerDao;
    
    /**
     * Constructor in parameter wait a {@link CrudDaoImpl} instance with its
     * interface type.
     * 
     * <p><strong>
     * If the parameter type implement an interface than must use it as a parameter type. 
     * Otherwise throw an {@link NoSuchBeanDefinitionException} exception!!!
     * </strong>
     * 
     * @param crudDao {@link DeveloperDaoImpl} instance 
     */
    public DeveloperServiceImpl(@Qualifier("developerDaoImpl") CrudDao<Developer, Long> crudDao) {
        super(crudDao);
        this.developerDao = (DeveloperDao) crudDao;
    }

    /**
     * Returns a {@code Developer} entity by the specified developer's ID.
     * @param developerId developer's ID
     * @return a {@code Developer} 
     */
    @Override
    public Developer getByDeveloperId(Long developerId) {
        return developerDao.getByDeveloperId(developerId);
    }

    /**
     * Returns a list of the {@code Developer} entity by the specified fore name.
     * @param foreName fore Name
     * @return a list of {@code Developer}
     */
    @Override
    public List<Developer> getAllByForename(String foreName) {
        return developerDao.getAllByForename(foreName);
    }

    /**
     * Update lastname by {@link Developer}'s {@code developerId}.
     * @param developerId the developer's ID
     * @param lastname the developer's lastname
     */
    @Override
    public void updateLastnameByDeveloperId(Long developerId, String lastname) {
        developerDao.updateLastnameByDeveloperId(developerId, lastname);
    }
    
    /**
     * Remove {@link Developer} entity with specified {@code developerId}.
     * @param developerId developer's ID
     */
    @Override
    public void removeByDeveloperId(Long developerId) {
        developerDao.removeByDeveloperId(developerId);
    }
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param developer developer
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isDeveloperExist(Developer developer) {
        return developerDao.isDeveloperExist(developer);
    }

}
