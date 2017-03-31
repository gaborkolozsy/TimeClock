/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

/**
 * Order some common variable and common method to builder classes.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <T> type of entity
 * @param <B> type of builder
 * @since 0.0.1-SNAPSHOT
 */
abstract class Entity<T, B> {

    /**
     * The specified {@TimeClock} entity.
     */
    public T entity;
    
    /**
     * Set the specified {@code TimeClock} entity's customer ID.
     * @param customerId ID of customer
     * @return the specified builder type
     */
    public B setCustomerId(int customerId) {
        return null;
    }
    
    /**
     * Set the specified {@code TimeClock} entity's developer ID.
     * @param developerId ID of developer
     * @return the specified builder type
     */
    public B setDeveloperId(int developerId) {
        return null;
    }
    
    /**
     * Set the specified {@code TimeClock} entity's pay ID.
     * @param payId ID of pay
     * @return the specified builder type
     */
    public B setPayId(int payId) {
        return null;
    }
    
    /**
     * Set the specified {@code TimeClock} entity's order No..
     * @param orderNumber No. of order
     * @return the specified builder type
     */
    public B setOrderNumber(int orderNumber) {
        return null;
    }
    
}
