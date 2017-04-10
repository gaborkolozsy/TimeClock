/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

import hu.gaborkolozsy.timeclock.model.Developer;
import hu.gaborkolozsy.timeclock.model.Job;
import hu.gaborkolozsy.timeclock.model.embedded.Address;
import java.util.List;

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
     * Set the specified {@code TimeClock} entity's developer.
     * @param developer developer
     * @return the specified builder type
     */
    public B setDeveloper(Developer developer) {
        return null;
    }
    
    /**
     * Set the specified {@code TimeClock} entity's job list.
     * @param jobs entity's jobs
     * @return the specified builder type
     */
    public B setJobs(List<Job> jobs) {
        return null;
    }
    
    /**
     * Set the specified {@code TimeClock} entity's {@code Address}.
     * @param address {@code Address}
     * @return the specified builder type
     */
    public B setAddress(Address address) {
        return null;
    }
    
}
