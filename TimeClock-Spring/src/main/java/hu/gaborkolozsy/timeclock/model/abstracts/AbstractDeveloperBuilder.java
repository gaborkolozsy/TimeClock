/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

import hu.gaborkolozsy.timeclock.model.Developer;
import hu.gaborkolozsy.timeclock.model.Developer.DeveloperBuilder;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import java.util.List;

/**
 * Order some abstract methode for {@link Developer} entity's 
 * {@link DeveloperBuilder} class.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <B> type of builder
 * @since 0.0.1-SNAPSHOT
 */
public abstract class AbstractDeveloperBuilder<E, B> extends Entity<E, B> implements Builder<E> {

    /**
     * Set the {@link Developer}'s natural ID.
     * @param developerId developer's natural ID
     * @return builder implement class
     */
    public abstract B setDeveloperId(int developerId);
    
    /**
     * Set the {@link Developer}'s forename.
     * @param forename developrs's forename
     * @return builder implement class
     */
    public abstract B setForename(String forename);
    
    /**
     * Set the {@link Developer}'s ast name.
     * @param lastName developer's last name
     * @return builder implement class
     */
    public abstract B setLastName(String lastName);
    
    /**
     * Set the {@link Developer}'s working hours list.
     * @param workingHours {@link Developer}'s working hours
     * @return builder implement class
     */
    public abstract B setWorkingHours(List<WorkingHours> workingHours);
    
}