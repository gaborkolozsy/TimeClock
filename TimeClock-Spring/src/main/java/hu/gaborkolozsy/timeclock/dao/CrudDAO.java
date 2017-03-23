/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */
package hu.gaborkolozsy.timeclock.dao;

/**
 * The crud generic dao interface.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <K> type of key
 * @since 0.0.1-SNAPSHOT
 */
public interface CrudDAO<E, K> {
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param entity entity instance
     * @return boolean indicating if entity is in persistence context
     */
    boolean isExist(E entity);

    /**
     * Make an instance managed and persistent.
     * @param entity entity instance
     */
    void add(E entity);

    /**
     * Merge the state of the given entity into the current persistence context.
     * @param entity entity instance
     */
    void update(E entity);
    
    /**
     * Find by primary key.
     * Search for an entity of (the specified class and) primary key.
     * If the entity instance is contained in the persistence context,
     * it is returned from there.
     * @param key primary key
     * @return the found entity instance or null if the entity does not exist
     */
    E get(K key);

    /**
     * Returns an iterable collection one of the {@code TimeClock} entity.
     * @return an iterable collection of entity
     */
    Iterable<E> getAll();
    
    /**
     * Remove the specified entity instance.
     * @param entity entity instance
     */
    void remove(E entity);
    
    /**
     * Clear persistence context.
     */
    void clear();
    
    /**
     * Close the entity manager.
     */
    void close();
    
}
