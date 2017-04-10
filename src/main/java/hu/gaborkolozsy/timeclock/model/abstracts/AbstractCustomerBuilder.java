/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

import hu.gaborkolozsy.timeclock.model.Customer;
import hu.gaborkolozsy.timeclock.model.Customer.CustomerBuilder;

/**
 * Order some abstract methode for {@link Customer} entity's 
 * {@link CustomerBuilder} class.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <B> type of builder
 * @since 0.0.1-SNAPSHOT
 */
public abstract class AbstractCustomerBuilder<E, B> extends Entity<E, B> implements Builder<E> {

    /**
     * Set the {@code Customer}'s natural ID.
     * @param customerId customer's ID
     * @return builder implement class
     */
    public abstract B setCustomerId(int customerId);
    
    /**
     * Set the {@code Customer}'s name.
     * @param name customer's name
     * @return builder implement class
     */
    public abstract B setName(String name);
    
    /**
     * Set the {@code Customer}'s contact person.
     * @param contact contact person
     * @return builder implement class
     */
    public abstract B setContact(String contact);
}
