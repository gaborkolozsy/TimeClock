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
public abstract class AbstractPayBuilder<E, B> extends AbstractEntity<E, B> implements Builder<E> {

    /**
     * Set the {@code Pay}'s ID.
     * @param payId pay's ID
     * @return builder implement class
     */
    public abstract B setPayId(String payId);
    
    /**
     * Set the {@code Pay}'s payment.
     * @param payment the payment
     * @return builder implement class
     */
    public abstract B setPayment(double payment);
    
    /**
     * Set the {@code Pay}'s currency.
     * @param currency currency
     * @return builder implement class
     */
    public abstract B setCurrency(String currency);
    
    /**
     * Set the {@code Pay}'s payment time.
     * @param paymentTime payment time
     * @return builder implement class
     */
    public abstract B setPaymentTime(LocalDateTime paymentTime);
    
    /**
     * Set the {@code Pay} is playable.
     * @param payable payable
     * @return builder implement class
     */
    public abstract B setPayable(boolean payable);
    
    /**
     * Set the {@code Pay} is paid.
     * @param paid paid
     * @return builder implement class
     */
    public abstract B setPaid(boolean paid);
    
    /**
     * Set the {@code Pay}'s relevant {@code Job}.
     * @param job job
     * @return builder implement class
     */
    public abstract B setJob(Job job);
    
}
