/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.controller.service.impl;

import hu.gaborkolozsy.timeclock.controller.dao.CommonDAO;
import hu.gaborkolozsy.timeclock.controller.dao.impl.CommonDAOImpl;
import hu.gaborkolozsy.timeclock.controller.service.CommonService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * The basic generic service implementation dependent with persistence context.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <T> type of entity
 * @param <ID> type of primary key
 * @since 0.0.1-SNAPSHOT
 * @see CommonDAO
 * @see CommonDAOImpl
 * @see List
 */
@Transactional
public class CommonServiceImpl<T, ID extends Serializable> implements CommonService<T, ID> {

    @Autowired
    private final CommonDAO<T, ID> commonDao;
    
    /**
     * Constructor wait a {@code CommonDAOImpl} instance but with interface type.
     * <p><strong>So can avoid the {@link NoSuchBeanDefinitionException} exception!!!</strong>
     * @param commonDao {@code CommonDAOImpl}
     */
    public CommonServiceImpl(CommonDAO<T, ID> commonDao) {
        this.commonDao = commonDao;
    }

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param entity entity instance
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isExist(T entity) {
        return commonDao.isExist(entity);
    }

    /**
     * Make an instance, managed and persistent. Return the entity's ID.
     * @param entity entity instance
     * @return the ID of entity instance
     */
    @Override
    public ID add(T entity) {
        return commonDao.add(entity);
    }

    /**
     * Merge the state of the given entity into the current persistence context.
     * @param entity entity instance 
     */
    @Override
    public void update(T entity) {
        commonDao.update(entity);
    }

    /**
     * Find by primary key.
     * Search for an entity of (the specified class and) primary key.
     * If the entity instance is contained in the persistence context,
     * it is returned from there.
     * @param primaryKey primary key
     * @return the found entity instance or null if the entity does not exist
     */
    @Override
    public T get(ID primaryKey) {
        return commonDao.get(primaryKey);
    }

    /**
     * Returns a list of the {@code TimeClock} entity.
     * @return a list of entity
     */
    @Override
    public List<T> getAll() {
        return commonDao.getAll();
    }

    /**
     * Remove the specified entity instance.
     * @param entity entity instance
     */
    @Override
    public void remove(T entity) {
        commonDao.remove(entity);
    }

    /**
     * Clear persistence context.
     */
    @Override
    public void clear() {
        commonDao.clear();
    }

    /**
     * Close the entity manager.
     */
    @Override
    public void close() {
        commonDao.close();
    }

}
