/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

/**
 * Order some common variable and common method to builder classes.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <B> type of builder
 * @since 0.0.1-SNAPSHOT
 */
abstract class Entity<E, B> {

    /**
     * The specified {@TimeClock} entity.
     */
    public E entity;
    
    /**
     * Set the specified {@code TimeClock} entity's developer ID.
     * @param developerId ID of developer
     * @return the specified builder type
     */
    public B setDeveloperId(int developerId) {
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
