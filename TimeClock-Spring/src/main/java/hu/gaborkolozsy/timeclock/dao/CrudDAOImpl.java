/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

/**
 * The basic DAO generic implementation dependent with persistence context.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <K> type of key
 * @since 0.0.1-SNAPSHOT
 * @see ParameterizedType
 * @see Type
 * @see EntityManager
 * @see PersistenceContext
 * @see CriteriaBuilder
 * @see CriteriaQuery
 * @see Repository
 */
@Repository
public abstract class CrudDAOImpl<E, K extends Serializable> implements CrudDAO<E, K> {
    
    /** Interface used to interact with the persistence context. */
    @PersistenceContext(unitName = "timeclock")
    EntityManager entityManager;

    /** An entity type. */
    private final Class<? extends E> entityType;
    
    /**
     * By defining this class as abstract, we prevent Spring from creating 
     * instance of this class. If not defined abstract 
     * getClass().getGenericSuperClass() would return Object.
     * There would be exception because Object class does not hava constructor 
     * with parameters.
     */
    public CrudDAOImpl() {
        Type type = getClass().getSuperclass();
        ParameterizedType pType = (ParameterizedType) type;
        entityType = (Class<? extends E>) pType.getActualTypeArguments()[0];
    }

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param entity entity instance
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isExist(E entity) {
        return entityManager.contains(entity);
    }
    
    /**
     * Make an instance managed and persistent.
     * @param entity entity instance
     */
    @Override
    public void add(E entity) {
        entityManager.persist(entity);
    }
    
    /**
     * Merge the state of the given entity into the current persistence context.
     * @param entity entity instance
     */
    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }
    
    /**
     * Find by primary key.
     * Search for an entity of (the specified class and) primary key.
     * If the entity instance is contained in the persistence context,
     * it is returned from there.
     * @param key primary key
     * @return the found entity instance or null if the entity does not exist
     */
    @Override
    public E get(K key) {
        return entityManager.find(entityType, key);
    }

    /**
     * Returns an iterable collections with the given entity.
     * @return an iterable collections of entity
     */
    @Override
    public Iterable<E> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery((Class<E>) entityType);
        return entityManager.createQuery(cq).getResultList();
    }
    
    /**
     * Remove the specified entity instance.
     * @param entity entity instance
     */
    @Override
    public void remove(E entity) {
        entityManager.remove(entity);
    }    

    /**
     * Clear the persistence context.
     */
    @Override
    public void clear() {
        entityManager.clear();
    }
    
    /**
     * Close the {@code EntityManager}.
     */
    @Override
    public void close() {
        entityManager.close();
    }
    
}
