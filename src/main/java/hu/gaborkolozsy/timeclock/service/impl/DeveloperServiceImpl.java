/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.dao.CrudDAO;
import hu.gaborkolozsy.timeclock.dao.DeveloperDAO;
import hu.gaborkolozsy.timeclock.dao.impl.CrudDAOImpl;
import hu.gaborkolozsy.timeclock.dao.impl.DeveloperDAOImpl;
import hu.gaborkolozsy.timeclock.model.Developer;
import hu.gaborkolozsy.timeclock.service.DeveloperService;
import java.util.List;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Developer service implementation. Connect between Controller and DAO.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see CrudDAO
 * @see DeveloperDAO
 * @see CrudDAOImpl
 * @see DeveloperDAOImpl
 * @see List
 * @see Autowired
 * @see Qualifier
 */
@Service
public class DeveloperServiceImpl extends CommonServiceImpl<Developer, Long> implements DeveloperService {

    @Autowired
    private final DeveloperDAO developerDao;
    
    /**
     * Constructor in parameter wait a {@link CrudDAOImpl} instance with its
     * interface type.
     * 
     * <p><strong>
     * If the parameter type implement an interface than must use it as a parameter type. 
     * Otherwise throw an {@link NoSuchBeanDefinitionException} exception!!!
     * </strong>
     * 
     * @param crudDao {@link DeveloperDAOImpl} instance 
     */
    public DeveloperServiceImpl(@Qualifier("developerDAOImpl") CrudDAO<Developer, Long> crudDao) {
        super(crudDao);
        this.developerDao = (DeveloperDAO) crudDao;
    }

    /**
     * Returns a {@code Developer} entity by the specified developer's ID.
     * @param developerID developer's ID
     * @return a {@code Developer} 
     */
    @Override
    public Developer getByDeveloperId(Integer developerID) {
        return developerDao.getByDeveloperId(developerID);
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
    public void updateLastnameByDeveloperId(Integer developerId, String lastname) {
        developerDao.updateLastnameByDeveloperId(developerId, lastname);
    }
    
    /**
     * Remove {@link Developer} entity with specified {@code developerId}.
     * @param developerId developer's ID
     */
    @Override
    public void removeByDeveloperId(Integer developerId) {
        developerDao.removeByDeveloperId(developerId);
    }
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param developerId developer'S ID
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isDeveloperExist(Integer developerId) {
        return developerDao.isDeveloperExist(developerId);
    }

}
