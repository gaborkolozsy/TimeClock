/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.dao.CrudDAO;
import hu.gaborkolozsy.timeclock.dao.PayDAO;
import hu.gaborkolozsy.timeclock.dao.impl.CrudDAOImpl;
import hu.gaborkolozsy.timeclock.model.Pay;
import hu.gaborkolozsy.timeclock.service.PayService;
import java.util.List;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Pay service implementation. Connect between Controller and DAO.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see CrudDAO
 * @see PayDAO
 * @see CrudDAOImpl
 * @see PayDAOImpl
 * @see List
 * @see Autowired
 * @see Qualifier
 */
@Service
public class PayServiceImpl extends CrudServiceImpl<Pay, Long> implements PayService {

    @Autowired
    private final PayDAO payDao;

    /**
     * Constructor in parameter wait a {@link CrudDAOImpl} instance with its
     * interface type.
     * 
     * <p><strong>
     * If the parameter type implement an interface than must use it as a parameter type. 
     * Otherwise throw an {@link NoSuchBeanDefinitionException} exception!!!
     * </strong>
     * 
     * @param crudDao {@link PayDAOImpl} instance 
     */
    public PayServiceImpl(@Qualifier("payDAOImpl") CrudDAO<Pay, Long> crudDao) {
        super(crudDao);
        this.payDao = (PayDAO) crudDao;
    }

    /**
     * Returns a {@code Pay} entity by the specified ID.
     * @param payId pay's ID
     * @return a {@code Pay} instance
     */
    @Override
    public Pay getByPayId(String payId) {
        return payDao.getByPayId(payId);
    }

    /**
     * Returns a list of the {@code Pay} entity by the given payable(true or false).
     * In other words: If job is done, than payable is true.
     * @param payable payable
     * @return a list of {@code Pay}
     */
    @Override
    public List<Pay> getByPayable(boolean payable) {
        return payDao.getByPayable(payable);
    }

    /**
     * Returns a list of the {@code Pay} entity by the given paid(true or false).
     * In other words: If job is done and customer paid it, than paid is true.
     * @param paid paid
     * @return a list of {@code Pay}
     */
    @Override
    public List<Pay> getByPaid(boolean paid) {
        return payDao.getByPaid(paid);
    }

    /**
     * Update {@link Pay}'s payment by specified pay ID.
     * @param payId pay's ID
     * @param payment payment
     */
    @Override
    public void updatePaymentByPayId(String payId, double payment) {
        payDao.updatePaymentByPayId(payId, payment);
    }

    /**
     * Update {@link Pay}'s payable by specified pay ID.
     * @param payId pay's ID
     * @param payable payable
     */
    @Override
    public void updatePayableByPayId(String payId, boolean payable) {
        payDao.updatePayableByPayId(payId, payable);
    }

    /**
     * Update {@link Pay}'s paid by specified pay ID.
     * @param payId pay's ID
     * @param paid paid
     */
    @Override
    public void updatePaidByPayId(String payId, boolean paid) {
        payDao.updatePaidByPayId(payId, paid);
    }

    /**
     * Remove {@link Pay} entity with specified pay ID.
     * @param payId pay's ID
     */
    @Override
    public void removeByPayId(String payId) {
        payDao.removeByPayId(payId);
    }

    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param payId pay's ID
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isPayExist(String payId) {
        return payDao.isPayExist(payId);
    }

    /**
     * Check specified {@link Pay} entity payable or not.
     * @param payId pay's ID
     * @return true if the relevant job is done
     */
    @Override
    public boolean isPayable(String payId) {
        return payDao.isPayable(payId);
    }

    /**
     * Check specified {@link Pay} entity paid or not.
     * @param payId pay's ID
     * @return true if the relevant job is done and paid by customer
     */
    @Override
    public boolean isPaid(String payId) {
        return payDao.isPaid(payId);
    }
        
}
