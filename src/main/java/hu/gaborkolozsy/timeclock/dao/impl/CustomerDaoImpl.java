/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.dao.impl;

import hu.gaborkolozsy.timeclock.model.Customer;
import hu.gaborkolozsy.timeclock.model.Customer.CustomerBuilder;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
import hu.gaborkolozsy.timeclock.dao.CustomerDao;

/**
 * Extends {@code CrudDaoImpl} and implement {@code CustomerDao}. 
 * Interact with persistence context (database).
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see CustomerBuilder
 */
@Repository
public class CustomerDaoImpl extends CrudDaoImpl<Customer, Long> implements CustomerDao {

    /**
     * Returns {@code Customer} by the specified customer's ID.
     * @param customerId customer's ID
     * @return {@code Customer} instance
     */
    @Override
    public Customer getByCustomerId(Integer customerId) throws PersistenceException {
        return entityManager.createNamedQuery("getByCustomerId", Customer.class)
                .setParameter("customerId", customerId)
                .getSingleResult();
    }

    /**
     * Returns {@code Customer} with the specified name.
     * @param name customer's name
     * @return {@code Customer} instance
     */
    @Override
    public Customer getByCustomerName(String name) throws PersistenceException {
        return entityManager.createNamedQuery("getByCustomerName", Customer.class)
                .setParameter("name", name)
                .getSingleResult();
    }
    
    /**
     * Update the {@code Customer}'s contact person by specified ID.
     * @param customerId customer's ID
     * @param contact contact person's name by customer
     */
    @Override
    public void updateContactByCustomerId(Integer customerId, String contact) {
        entityManager.merge(new CustomerBuilder(getByCustomerId(customerId))
                .setContact(contact)
                .build());
    }

    /**
     * Remove {@code Customer} by the specified customer's ID.
     * @param customerId customer's ID
     */
    @Override
    public void removeByCustomerId(Integer customerId) {
        entityManager.remove(getByCustomerId(customerId));
    }
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param customerId customer's ID
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isCustomerExist(Integer customerId) {
        return getByCustomerId(customerId) != null;
    }

}
