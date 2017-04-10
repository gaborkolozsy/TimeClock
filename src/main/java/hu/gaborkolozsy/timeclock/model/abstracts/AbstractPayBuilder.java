/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

import hu.gaborkolozsy.timeclock.model.Job;
import hu.gaborkolozsy.timeclock.model.Pay;
import hu.gaborkolozsy.timeclock.model.Pay.PayBuilder;
import java.time.LocalDateTime;

/**
 * Order some abstract methode for {@link Pay} entity's {@link PayBuilder} 
 * class.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <B> type of builder
 * @since 0.0.1-SNAPSHOT
 * @see Job
 * @see LocalDateTime
 */
public abstract class AbstractPayBuilder<E, B> extends Entity<E, B> implements Builder<E> {

    /**
     * Set the pay.
     * @param pay the pay
     * @return builder implement class
     */
    public abstract B setPay(int pay);
    
    /**
     * Set the currency.
     * @param currency currency
     * @return builder implement class
     */
    public abstract B setCurrency(String currency);
    
    /**
     * Set the pay time.
     * @param payTime pay time
     * @return builder implement class
     */
    public abstract B setPayTime(LocalDateTime payTime);
    
    /**
     * Set playable.
     * @param payable payable
     * @return builder implement class
     */
    public abstract B isPayable(boolean payable);
    
    /**
     * Set paid.
     * @param paid paid
     * @return builder implement class
     */
    public abstract B isPaid(boolean paid);
    
    /**
     * Set the {@code Pay}'s relevant {@code Job}.
     * @param job job
     * @return builder implement class
     */
    public abstract B setJob(Job job);
    
}
