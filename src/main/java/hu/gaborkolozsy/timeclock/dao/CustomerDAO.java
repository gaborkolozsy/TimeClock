/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.dao;

import hu.gaborkolozsy.timeclock.model.Customer;
import javax.persistence.PersistenceException;

/**
 * Extended {@code CustomerDAO} interface.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
public interface CustomerDAO extends CrudDAO<Customer, Long> {

    /**
     * Returns {@code Customer} by the specified customer's ID.
     * @param customerId customer's ID
     * @return {@code Customer} instance
     */
    Customer getByCustomerId(Integer customerId) throws PersistenceException;
    
    /**
     * Returns {@code Customer} with the specified name.
     * @param name customer's name
     * @return {@code Customer} instance
     */
    Customer getByCustomerName(String name) throws PersistenceException;
    
    /**
     * Update the {@code Customer}'s contact person by specified ID.
     * @param customerId customer's ID
     * @param contact contact person's name by customer
     */
    void updateContactByCustomerId(Integer customerId, String contact);
    
    /**
     * Remove {@code Customer} by the specified customer's ID.
     * @param customerId customer's ID
     */
    void removeByCustomerId(Integer customerId);
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param customerId customer's ID
     * @return boolean indicating if entity is in persistence context
     */
    boolean isCustomerExist(Integer customerId);
    
}
