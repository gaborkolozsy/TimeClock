/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.dao.CrudDAO;
import hu.gaborkolozsy.timeclock.dao.CustomerDAO;
import hu.gaborkolozsy.timeclock.dao.impl.CrudDAOImpl;
import hu.gaborkolozsy.timeclock.dao.impl.CustomerDAOImpl;
import hu.gaborkolozsy.timeclock.model.Customer;
import hu.gaborkolozsy.timeclock.service.CustomerService;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Customer service implementation. Connect between Controller and DAO.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see CrudDAO
 * @see CustomerDAO
 * @see CrudDAOImpl
 * @see CustomerDAOImpl
 * @see Autowired
 * @see Qualifier
 */
@Service
public class CustomerServiceImpl extends CrudServiceImpl<Customer, Long> implements CustomerService {

    @Autowired
    private final CustomerDAO customerDao;

    /**
     * Constructor in parameter wait a {@link CrudDAOImpl} instance with its
     * interface type.
     * 
     * <p><strong>
     * If the parameter type implement an interface than must use it as a parameter type. 
     * Otherwise throw an {@link NoSuchBeanDefinitionException} exception!!!
     * </strong>
     * 
     * @param crudDao {@link CustomerDAOImpl} instance 
     */
    public CustomerServiceImpl(@Qualifier("customerDAOImpl") CrudDAO<Customer, Long> crudDao) {
        super(crudDao);
        this.customerDao = (CustomerDAO) crudDao;
    }

    /**
     * Returns {@code Customer} by the specified customer's ID.
     * @param customerId customer's ID
     * @return {@code Customer} instance
     */
    @Override
    public Customer getByCustomerId(Integer customerId) {
        return customerDao.getByCustomerId(customerId);
    }

    /**
     * Returns {@code Customer} with the specified name.
     * @param name customer's name
     * @return {@code Customer} instance
     */
    @Override
    public Customer getByCustomerName(String name) {
        return customerDao.getByCustomerName(name);
    }
    
    /**
     * Update the {@code Customer}'s contact person by specified ID.
     * @param customerId customer's ID
     * @param contact contact person's name by customer
     */
    @Override
    public void updateContactByCustomerId(Integer customerId, String contact) {
        customerDao.updateContactByCustomerId(customerId, contact);
    }

    /**
     * Remove {@code Customer} by the specified customer's ID.
     * @param customerId customer's ID
     */
    @Override
    public void removeByCustomerId(Integer customerId) {
        customerDao.removeByCustomerId(customerId);
    }
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param customerId customer's ID
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isCustomerExist(Integer customerId) {
        return customerDao.isCustomerExist(customerId);
    }
    
}
