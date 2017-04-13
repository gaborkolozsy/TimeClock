/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

import hu.gaborkolozsy.timeclock.model.embedded.Audit;
import hu.gaborkolozsy.timeclock.model.embedded.Audit.AuditBuilder;
import java.time.LocalDateTime;

/**
 * Order some abstract methode for {@link Audit} entity's 
 * {@link AuditBuilder} class.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <B> type of builder
 * @since 0.0.1-SNAPSHOT
 * @see LocalDateTime
 */
public abstract class AbstractAuditBuilder<E, B> extends AbstractEntity<E, B> 
        implements Builder<E> {

    /**
     * Set the created.
     * @param created created
     * @return builder implement class
     */
    public abstract B setCreated(LocalDateTime created);
    
    /**
     * Set the created by.
     * @param createdBy created by
     * @return builder implement class
     */
    public abstract B setCreatedBy(String createdBy);
    
    /**
     * Set the updated.
     * @param updated updated
     * @return builder implement class
     */
    public abstract B setUpdated(LocalDateTime updated);
    
    /**
     * Set the updated by.
     * @param updatedBy updated by
     * @return builder implement class
     */
    public abstract B setUpdatedBy(String updatedBy);
    
}
