/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.dao;

import hu.gaborkolozsy.timeclock.model.Pay;
import java.util.List;

/**
 * Extended {@code PayDao} interface.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see List
 */
public interface PayDao extends CrudDao<Pay, Long> {

    /**
     * Returns a {@code Pay} entity by the specified ID.
     * @param payId pay's ID
     * @return a {@code Pay} instance
     */
    Pay getByPayId(String payId);
    
    /**
     * Returns a list of the {@code Pay} entity by the given payable(true or false).
     * In other words: If job is done, than payable is true.
     * @param payable payable
     * @return a list of {@code Pay}
     */
    List<Pay> getByPayable(boolean payable);
    
    /**
     * Returns a list of the {@code Pay} entity by the given paid(true or false).
     * In other words: If job is done and customer paid it, than paid is true.
     * @param paid paid
     * @return a list of {@code Pay}
     */
    List<Pay> getByPaid(boolean paid);
    
    /**
     * Update {@link Pay}'s payment by specified pay ID.
     * @param payId pay's ID
     * @param payment payment
     */
    void updatePaymentByPayId(String payId, double payment);
    
    /**
     * Update {@link Pay}'s payable by specified pay ID.
     * @param payId pay's ID
     * @param payable payable
     */
    void updatePayableByPayId(String payId, boolean payable);
    
    /**
     * Update {@link Pay}'s paid by specified pay ID.
     * @param payId pay's ID
     * @param paid paid
     */
    void updatePaidByPayId(String payId, boolean paid);
    
    /**
     * Remove {@link Pay} entity with specified pay ID.
     * @param payId pay's ID
     */
    void removeByPayId(String payId);
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param payId pay's ID
     * @return boolean indicating if entity is in persistence context
     */
    boolean isExistWithPayId(String payId);
    
    /**
     * Check specified {@link Pay} entity payable or not.
     * @param payId pay's ID
     * @return true if the relevant job is done
     */
    boolean isPayable(String payId);
    
    /**
     * Check specified {@link Pay} entity paid or not.
     * @param payId pay's ID
     * @return true if the relevant job is done and paid by customer
     */
    boolean isPaid(String payId);
    
}
