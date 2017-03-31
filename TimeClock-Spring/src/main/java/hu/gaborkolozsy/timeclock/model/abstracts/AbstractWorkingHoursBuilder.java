/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.model.WorkingHours.WorkingHoursBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Order some abstract methode for {@link WorkingHours} entity's 
 * {@link WorkingHoursBuilder} class.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <B> type of builder
 * @since 0.0.1-SNAPSHOT
 * @see LocalDate
 * @see LocalDateTime 
 */
public abstract class AbstractWorkingHoursBuilder<E, B> extends Entity<E, B> implements Builder<E> {
    
    /**
     * Set the actual day.
     * @param day the actual day
     * @return builder implement class
     */
    public abstract B setDay(LocalDate day);
    
    /**
     * Set the time at the start of work.
     * @param start the time at the start of work
     * @return builder implement class
     */
    public abstract B setWorkStart(LocalDateTime start); 
    
    /**
     * Set the time at the end of work.
     * @param end the time at the end of work
     * @return builder implement class
     */
    public abstract B setWorkEnd(LocalDateTime end);
    
}