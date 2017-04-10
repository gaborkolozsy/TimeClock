/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.dao.impl;

import hu.gaborkolozsy.timeclock.dao.PayDAO;
import hu.gaborkolozsy.timeclock.model.Pay;
import hu.gaborkolozsy.timeclock.model.Pay.PayBuilder;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Extends {@code CrudDAOImpl} and implement {@code PayDAO}. 
 * Interact with persistence context (database).
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see PayBuilder
 * @see List
 */
@Repository
public class PayDAOImpl extends CrudDAOImpl<Pay, Long> implements PayDAO {

    /**
     * Returns a {@code Pay} entity by the specified ID.
     * @param payId pay's ID
     * @return a {@code Pay} instance
     */
    @Override
    public Pay getByPayId(String payId) {
        return entityManager.createNamedQuery("getByPayId", Pay.class)
                .setParameter("payId", payId)
                .getSingleResult();
    }

    /**
     * Returns a list of the {@code Pay} entity by the given payable(true or false).
     * In other words: If job is done, than payable is true.
     * @param payable payable
     * @return a list of {@code Pay}
     */
    @Override
    public List<Pay> getByPayable(boolean payable) {
        return entityManager.createNamedQuery("getByPayable", Pay.class)
                .setParameter("payable", payable)
                .getResultList();
    }

    /**
     * Returns a list of the {@code Pay} entity by the given paid(true or false).
     * In other words: If job is done and customer paid it, than paid is true.
     * @param paid paid
     * @return a list of {@code Pay}
     */
    @Override
    public List<Pay> getByPaid(boolean paid) {
        return entityManager.createNamedQuery("getByPaid", Pay.class)
                .setParameter("paid", paid)
                .getResultList();
    }

    /**
     * Update {@link Pay}'s payment by specified pay ID.
     * @param payId pay's ID
     * @param payment payment
     */
    @Override
    public void updatePaymentByPayId(String payId, double payment) {
        entityManager.merge(new PayBuilder(getByPayId(payId))
                .setPayment(payment)
                .build());
    }

    /**
     * Update {@link Pay}'s payable by specified pay ID.
     * @param payId pay's ID
     * @param payable payable
     */
    @Override
    public void updatePayableByPayId(String payId, boolean payable) {
        entityManager.merge(new PayBuilder(getByPayId(payId))
                .setPayable(payable)
                .build());
    }

    /**
     * Update {@link Pay}'s paid by specified pay ID.
     * @param payId pay's ID
     * @param paid paid
     */
    @Override
    public void updatePaidByPayId(String payId, boolean paid) {
        entityManager.merge(new PayBuilder(getByPayId(payId))
                .setPayable(paid)
                .build());
    }

    /**
     * Remove {@link Pay} entity with specified pay ID.
     * @param payId pay's ID
     */
    @Override
    public void removeByPayId(String payId) {
        entityManager.remove(getByPayId(payId));
    }

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param payId pay's ID
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isPayExist(String payId) {
        return getByPayId(payId) != null;
    }

    /**
     * Check specified {@link Pay} entity payable or not.
     * @param payId pay's ID
     * @return true if the relevant job is done
     */
    @Override
    public boolean isPayable(String payId) {
        return getByPayId(payId).isPayable();
    }

    /**
     * Check specified {@link Pay} entity paid or not.
     * @param payId pay's ID
     * @return true if the relevant job is done and paid by customer
     */
    @Override
    public boolean isPaid(String payId) {
        return getByPayId(payId).isPaid();
    }
    
}
