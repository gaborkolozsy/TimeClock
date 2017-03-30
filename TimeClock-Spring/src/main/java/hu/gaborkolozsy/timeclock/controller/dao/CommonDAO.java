/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */
package hu.gaborkolozsy.timeclock.controller.dao;

import java.util.List;

/**
 * The common generic dao interface.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <T> type of entity
 * @param <ID> type of primary key
 * @since 0.0.1-SNAPSHOT
 * @see List
 */
public interface CommonDAO<T, ID> {
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param entity entity instance
     * @return boolean indicating if entity is in persistence context
     */
    boolean isExist(T entity);

    /**
     * Make an instance, managed and persistent.
     * @param entity entity instance
     */
    void add(T entity);

    /**
     * Merge the state of the given entity into the current persistence context.
     * @param entity entity instance
     */
    void update(T entity);
    
    /**
     * Find by primary key.
     * Search for an entity of (the specified class and) primary key.
     * If the entity instance is contained in the persistence context,
     * it is returned from there.
     * @param primaryKey primary key
     * @return the found entity instance or null if the entity does not exist
     */
    T get(ID primaryKey);

    /**
     * Returns a list of the {@code TimeClock} entity.
     * @return a list of entity
     */
    List<T> getAll();
    
    /**
     * Remove the specified entity instance.
     * @param entity entity instance
     */
    void remove(T entity);
    
    /**
     * Clear persistence context.
     */
    void clear();
    
    /**
     * Close the entity manager.
     */
    void close();
    
}
