/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.controller.dao.impl;

import hu.gaborkolozsy.timeclock.controller.dao.CommonDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * The basic generic DAO implementation dependent with persistence context.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <T> type of entity
 * @param <ID> type of primary key
 * @since 0.0.1-SNAPSHOT
 * @see ParameterizedType
 * @see Type
 * @see List
 * @see EntityManager
 * @see PersistenceContext
 * @see CriteriaBuilder
 * @see CriteriaQuery
 */
public class CommonDAOImpl<T, ID extends Serializable> implements CommonDAO<T, ID> {
    
    /** Interface used to interact with the persistence context. */
    @PersistenceContext(unitName = "timeclock")
    EntityManager entityManager;

    /** An entity type. */
    private final Class<? extends T> entityType;
    
    /**
     * By defining this class as abstract, we prevent Spring from creating 
     * instance of this class. 
     * 
     * <p><i>Note</i>: If not defined abstract 
     * getClass().getGenericSuperClass() would return Object.
     * There would be exception because Object class does not hava constructor 
     * with parameters.
     * 
     * <pre>
     *      Or can use in this form too:
     *      
     *      this.entityType = (Class&#060;T&#062;) ((ParameterizedType) 
     *      getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     * 
     * </pre>
     */
    public CommonDAOImpl() { 
        Type type = getClass().getGenericSuperclass();
        ParameterizedType pType = (ParameterizedType) type; 
        this.entityType = (Class<T>) pType.getActualTypeArguments()[0];
        //this.entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param entity entity instance
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isExist(T entity) {
        return entityManager.contains(entity);
    }
    
    /**
     * Make an instance, managed and persistent.
     * @param entity entity instance
     */
    @Override
    public void add(T entity) {
        entityManager.persist(entity);
    }
    
    /**
     * Merge the state of the given entity into the current persistence context.
     * @param entity entity instance
     */
    @Override
    public void update(T entity) {
        entityManager.merge(entity);
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
        return entityManager.find(entityType, primaryKey);
    }

    /**
     * Returns a list with the given entity.
     * 
     * <p><pre>
     *      Or can use in this form too:
     *      
     *      return entityManager.createQuery
     *      ("Select t from " + entityType.getSimpleName() + " t").getResultList();
     * 
     * </pre>
     * @return a list of entity
     */
    @Override
    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery((Class<T>) entityType);
        Root<T> root = criteriaQuery.from((Class<T>) entityType);
        CriteriaQuery<T> all = criteriaQuery.select(root);
        return entityManager.createQuery(all).getResultList();
    }
    
    /**
     * Remove the specified entity instance.
     * @param entity entity instance
     */
    @Override
    public void remove(T entity) {
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
